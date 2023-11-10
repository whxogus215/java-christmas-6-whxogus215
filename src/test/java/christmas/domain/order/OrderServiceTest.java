package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.discount.DiscountType;
import christmas.domain.menu.Menu;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderServiceTest {
    OrderService service;

    @BeforeEach
    void before() {
        service = new OrderService();
    }

    @DisplayName("주문 서비스에서 할인 전 총주문 금액 확인")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 티본스테이크, 초코케이크, 제로콜라, 12, 2, 3, 1",
            "타파스, 바비큐립, 아이스크림, 샴페인, 1, 2, 13, 2"
    })
    void orderServiceTotalPriceTest(String menu1, String menu2, String menu3, String menu4,
                                    int quantity1, int quantity2, int quantity3, int quantity4) {
        String[] menuTypes = {menu1, menu2, menu3, menu4};
        int[] quantities = {quantity1, quantity2, quantity3, quantity4};
        Order order = new Order(menuTypes, quantities);

        int orderPrice = 0;
        for (String menu : order.getOrders().keySet()) {
            Menu findMenu = Menu.findMenuByName(menu);
            orderPrice += (order.getOrders().get(menu) * findMenu.getPrice());
        }
        service.order(menuTypes, quantities);

        assertThat(orderPrice).isEqualTo(service.getTotalOrderAmount());
    }

    @DisplayName("총 혜택 목록 반환 확인")
    @ParameterizedTest
    @CsvSource({
            "크리스마스파스타, 티본스테이크, 초코케이크, 제로콜라, 1, 2, 3, 1",
            "해산물파스타, 바비큐립, 아이스크림, 샴페인, 1, 2, 13, 2"
    })
    void orderServiceTotalBenefitTest(String menu1, String menu2, String menu3, String menu4,
                                    int quantity1, int quantity2, int quantity3, int quantity4) {
        String[] menuTypes = {menu1, menu2, menu3, menu4};
        int[] quantities = {quantity1, quantity2, quantity3, quantity4};

        service.order(menuTypes, quantities);
        Map<DiscountType, Integer> totalBenefit = service.getBenefitsWithoutGift(8);

        assertThat(totalBenefit.get(DiscountType.WEEKEND)).isEqualTo(2023 * 3);
    }
}
