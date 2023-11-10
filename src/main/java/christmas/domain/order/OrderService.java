package christmas.domain.order;

import christmas.domain.Order;
import christmas.domain.discount.DiscountType;
import christmas.domain.event.EventCheck;
import christmas.domain.menu.Menu;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final EventCheck eventCheck;
    private int totalOrderPrice;
    private int totalDiscountPrice;

    public OrderService() {
        eventCheck = new EventCheck();
    }

    public void order(String[] menuTypes, int[] quantities) {
        Order order = createOrder(menuTypes, quantities);
        Map<String, Integer> orders = order.getOrders();
        for (String menu : orders.keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            this.totalOrderPrice += (orders.get(menu) * findMenu.getPrice());
        }
    }

    public List<DiscountType> getDiscountTypes(int day) {
        List<DiscountType> discountTypes = eventCheck.checkDiscountType(day);
        for (DiscountType discountType : discountTypes) {
            totalDiscountPrice += discountType.getDiscountPrice(day);
        }
        return discountTypes;
    }

    private Order createOrder(String[] menuTypes, int[] quantities) {
        return new Order(menuTypes, quantities);
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }
}
