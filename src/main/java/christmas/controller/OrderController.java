package christmas.controller;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderService;
import christmas.utils.Converter;
import christmas.utils.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService service;

    private int date;
    private String[] menuNames;
    private int[] quantities;

    public OrderController(InputView inputView, OutputView outputView, OrderService service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.service = service;
    }

    public void placeOrderRequest() {
        receiveOrder();
        submitOrder();
        showOrderResult();
    }

    private void receiveOrder() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        date = getDate();
        while (true) {
            try {
                String orders = getOrders();
                quantities = Converter.getQuantities(orders);
                menuNames = Converter.getMenuNames(orders);
                validateMenuType(menuNames);
                validateAllDrinkInOrder(menuNames);
                validateQuantity(quantities);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void submitOrder() {
        service.order(menuNames, quantities);
    }

    private void showOrderResult() {
        outputView.printFirstNotice(date);

        showBeforeDiscountOrder();
        showAfterDiscountOrder();
    }

    private void showBeforeDiscountOrder() {
        outputView.printMenu(service.getOrderResult());
        outputView.printNotDiscountedTotalPrice(service.getTotalOrderAmount());
    }

    private void showAfterDiscountOrder() {
        Map<Menu, Integer> gift = service.getEventGift();
        outputView.printGiftCatalog(gift);

        Map<String, Integer> allBenefit = service.getAllBenefit(date);
        outputView.printAllBenefits(allBenefit);
        outputView.printAllBenefitPrice(service.getTotalBenefitPrice(date));
        outputView.printDiscountedTotalPrice(service.getDiscountedTotalPrice());

        outputView.printEventBadge(service.getEventBadge().getName());
    }

    private int getDate() {
        int date;
        while (true) {
            try {
                date = inputView.readDate();
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return date;
    }

    private String getOrders() {
        String orders;
        while (true) {
            try {
                orders = inputView.readMenuAndCount();
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return orders;
    }

    private void validateMenuType(String[] menuTypes) {
        validateIsInMenu(menuTypes);
        validateDuplicateInOrder(menuTypes);
    }

    private void validateIsInMenu(String[] menuTypes) {
        for (String menuType : menuTypes) {
            if (Menu.isNotInMenu(menuType)) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.REPEAT_ORDER.getMessage());
            }
        }
    }

    private void validateDuplicateInOrder(String[] menuTypes) {
        Set<String> menus = new HashSet<>(List.of(menuTypes));
        if (menus.size() != menuTypes.length) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.REPEAT_ORDER.getMessage());
        }
    }

    private void validateQuantity(int[] quantities) {
        validateMinCount(quantities);
        validateMaxQuantity(quantities);
    }

    private void validateMinCount(int[] quantities) {
        for (int quantity : quantities) {
            if (quantity < 1) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.REPEAT_ORDER.getMessage());
            }
        }
    }

    private void validateMaxQuantity(int[] quantities) {
        int sum = Arrays.stream(quantities).sum();
        if (sum > 20) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.MAX_ORDER.getMessage());
        }
    }

    private void validateAllDrinkInOrder(String[] menuTypes) {
        List<String> drinkMenu = Menu.getDrinkMenu();
        List<String> menus = List.of(menuTypes);
        if (drinkMenu.containsAll(menus)) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.NOT_ALL_DRINK.getMessage());
        }
    }
}
