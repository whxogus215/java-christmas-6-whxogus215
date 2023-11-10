package christmas;

import christmas.domain.menu.Menu;
import christmas.domain.order.OrderService;
import christmas.utils.Converter;
import christmas.utils.ErrorMessage;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

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

    public void reserveOrders() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        date = getDate();
        while (true) {
            try {
                String orders = getOrders();
                quantities = Converter.getQuantities(orders);
                menuNames = Converter.getMenuNames(orders);
                validateIsInMenu(menuNames);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void showResult() {
        outputView.printFirstNotice(date);
        Map<String, Integer> orders = service.order(menuNames, quantities);
        outputView.printMenu(orders);
        outputView.printNotDiscountedTotalPrice(service.getTotalOrderAmount());
        Map<Menu, Integer> gift = service.getEventGift();
        outputView.printGiftCatalog(gift);
        Map<String, Integer> allBenefit = service.createAllBenefit(date);
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

    private void validateIsInMenu(String[] menuTypes) {
        for (String menuType : menuTypes) {
            if (Menu.isNotInMenu(menuType)) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.NOT_IN_MENU.getMessage());
            }
        }
    }
}
