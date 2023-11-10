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
    private int totalOrderPrice;
    private int totalBenefitPrice;
    private Order order;

    public OrderService() {
        eventCheck = new EventCheck();
    }

    public Map<String, Integer> order(String[] menuTypes, int[] quantities) {
        order = createOrder(menuTypes, quantities);
        Map<String, Integer> orders = order.getOrders();
        for (String menu : orders.keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            this.totalOrderPrice += (orders.get(menu) * findMenu.getPrice());
        }
        return order.getOrders();
    }

    public Map<String, Integer> createAllBenefit(int date) {
        Map<String, Integer> allBenefits = new HashMap<>();
        Map<DiscountType, Integer> benefits = getTotalBenefit(date);
        for (DiscountType type : benefits.keySet()) {
            allBenefits.put(type.getDiscountName(), benefits.get(type));
        }
        Map<Menu, Integer> gifts = getEventGift();
        for (Menu menu : gifts.keySet()) {
            int giftPrice = gifts.get(menu) * menu.getPrice();
            allBenefits.put(menu.getName(), giftPrice);
        }
        return allBenefits;
    }

    public Map<DiscountType, Integer> getTotalBenefit(int date) {
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
            return price * menuTypes.get(MenuType.Dessert);
        }
        if (type.equals(DiscountType.WEEKEND)) {
            return price * menuTypes.get(MenuType.Main);
        }
        return price;
    }

    public List<DiscountType> getDiscountTypes(int date) {
        return eventCheck.checkDiscountType(date);
    }

    public Map<Menu, Integer> getEventGift() {
        Map<Menu, Integer> gifts = eventCheck.checkGift(totalOrderPrice);
        return gifts;
    }

    private void calculateGiftPrice(Map<Menu, Integer> gifts) {
        for (Entry<Menu, Integer> entry : gifts.entrySet()) {
            Menu giftMenu = entry.getKey();
            Integer quantity = entry.getValue();
            totalBenefitPrice += giftMenu.getPrice() * quantity;
        }
    }

    public EventBadge getEventBadge() {
        return eventCheck.checkBadge(totalBenefitPrice);
    }

    private Order createOrder(String[] menuTypes, int[] quantities) {
        return new Order(menuTypes, quantities);
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    public int getDiscountedTotalPrice() {
        Map<Menu, Integer> gifts = getEventGift();
        int giftTotalPrice = 0;
        for (Menu menu : gifts.keySet()) {
            giftTotalPrice += (menu.getPrice() * gifts.get(menu));
        }
        return totalOrderPrice - totalBenefitPrice - giftTotalPrice;
    }
}
