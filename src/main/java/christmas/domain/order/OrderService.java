package christmas.domain.order;

import christmas.domain.Order;
import christmas.domain.menu.Menu;
import java.util.Map;

public class OrderService {
    private int totalOrderPrice;
    private int totalDiscountPrice;

    public void order(String[] menuTypes, int[] quantities) {
        Order order = createOrder(menuTypes, quantities);
        Map<String, Integer> orders = order.getOrders();
        for (String menu : orders.keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            this.totalOrderPrice += (orders.get(menu) * findMenu.getPrice());
        }
    }

    private Order createOrder(String[] menuTypes, int[] quantities) {
        return new Order(menuTypes, quantities);
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }
}
