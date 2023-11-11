package christmas.domain.order;

import christmas.domain.Order;
import christmas.domain.discount.DiscountType;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventCheck;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderService {
    private final EventCheck eventCheck;
    private int totalOrderAmount;
    private int totalBenefitAmount;
    private Order order;

    public OrderService() {
        eventCheck = new EventCheck();
    }

    public Map<String, Integer> order(String[] menuTypes, int[] quantities) {
        order = createOrder(menuTypes, quantities);
        Map<String, Integer> orders = order.getOrders();
        for (String menu : orders.keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            this.totalOrderAmount += (orders.get(menu) * findMenu.getPrice());
        }
        return order.getOrders();
    }

    public Map<String, Integer> createAllBenefit(int date) {
        Map<String, Integer> allBenefits = new HashMap<>();
        Map<DiscountType, Integer> benefits = getBenefitsWithoutGift(date);
        for (DiscountType type : benefits.keySet()) {
            allBenefits.put(type.getDiscountName(), benefits.get(type));
        }
        Map<Menu, Integer> gifts = getEventGift();
        for (Menu menu : gifts.keySet()) {
            int giftPrice = gifts.get(menu) * menu.getPrice();
            allBenefits.put("증정 이벤트", giftPrice);
        }
        return allBenefits;
    }

    public Map<DiscountType, Integer> getBenefitsWithoutGift(int date) {
        Map<DiscountType, Integer> benefits = new HashMap<>();
        Map<MenuType, Integer> dessertAndMainQuantity = order.getDessertAndMainQuantity();
        List<DiscountType> discountTypes = getDiscountTypes(date);
        for (DiscountType discountType : discountTypes) {
            int price = discountType.getDiscountPrice(date);
            int totalPrice = calculateTotalPrice(discountType, price, dessertAndMainQuantity);
            benefits.put(discountType, totalPrice);
        }
        return benefits;
    }

    private int calculateTotalPrice(DiscountType type, int price,
                                    Map<MenuType, Integer> menuTypes) {
        if (type.equals(DiscountType.WEEKDAY)) {
            return price * menuTypes.getOrDefault(MenuType.DESSERT, 0);
        }
        if (type.equals(DiscountType.WEEKEND)) {
            return price * menuTypes.getOrDefault(MenuType.MAIN, 0);
        }
        return price;
    }

    public List<DiscountType> getDiscountTypes(int date) {
        return eventCheck.checkDiscountType(date);
    }

    public Map<Menu, Integer> getEventGift() {
        Map<Menu, Integer> gifts = eventCheck.checkGift(totalOrderAmount);
        return gifts;
    }

    public int getTotalBenefitPrice(int date) {
        calculateGiftPrice(getEventGift());
        calculateBenefit(date);
        return totalBenefitAmount;
    }

    private void calculateGiftPrice(Map<Menu, Integer> gifts) {
        for (Entry<Menu, Integer> entry : gifts.entrySet()) {
            Menu giftMenu = entry.getKey();
            Integer quantity = entry.getValue();
            totalBenefitAmount += giftMenu.getPrice() * quantity;
        }
    }

    private void calculateBenefit(int date) {
        Map<DiscountType, Integer> totalBenefit = getBenefitsWithoutGift(date);
        for (DiscountType type : totalBenefit.keySet()) {
            int quantity = totalBenefit.get(type) / type.getDiscountPrice(date);
            totalBenefitAmount += type.getDiscountPrice(date) * quantity;
        }
    }

    public EventBadge getEventBadge() {
        return eventCheck.checkBadge(totalBenefitAmount);
    }

    private Order createOrder(String[] menuTypes, int[] quantities) {
        return new Order(menuTypes, quantities);
    }

    public int getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public int getDiscountedTotalPrice() {
        Map<Menu, Integer> gifts = getEventGift();
        int giftTotalPrice = 0;
        for (Menu menu : gifts.keySet()) {
            giftTotalPrice += (menu.getPrice() * gifts.get(menu));
        }
        return totalOrderAmount - totalBenefitAmount + giftTotalPrice;
    }
}
