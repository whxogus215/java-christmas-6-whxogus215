package christmas;

import christmas.domain.order.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        OrderService orderService = new OrderService();
        OrderController controller = new OrderController(inputView, outputView, orderService);

        controller.placeOrderRequest();
    }
}
