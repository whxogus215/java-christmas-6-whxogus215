package christmas.domain.order;

import christmas.domain.Order;
import christmas.domain.discount.DiscountType;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventCheck;
import christmas.domain.menu.Menu;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderService {
    private final EventCheck eventCheck;
    private int totalOrderPrice;
    private int totalBenefitPrice;

    public OrderService() {
        eventCheck = new EventCheck();
    }

    public Map<String, Integer> order(String[] menuTypes, int[] quantities) {
        Order order = createOrder(menuTypes, quantities);
        Map<String, Integer> orders = order.getOrders();
        for (String menu : orders.keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            this.totalOrderPrice += (orders.get(menu) * findMenu.getPrice());
        }
        return order.getOrders();
    }

    public List<DiscountType> getDiscountTypes(int day) {
        List<DiscountType> discountTypes = eventCheck.checkDiscountType(day);
        calculateDiscountPrice(day, discountTypes);
        return discountTypes;
    }

    private void calculateDiscountPrice(int day, List<DiscountType> discountTypes) {
        for (DiscountType discountType : discountTypes) {
            totalBenefitPrice += discountType.getDiscountPrice(day);
        }
    }

    public Map<Menu, Integer> getEventGift() {
        Map<Menu, Integer> gifts = eventCheck.checkGift(totalOrderPrice);
        calculateGiftPrice(gifts);
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
