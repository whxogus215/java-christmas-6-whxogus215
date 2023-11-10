package christmas;

import christmas.domain.order.OrderService;
import christmas.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OrderService orderService = new OrderService();
        OrderController controller = new OrderController(inputView, orderService);

        controller.reserveOrders();
    }
}
