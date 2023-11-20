package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.MenuType;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTest {
    @DisplayName("메뉴가 정상적으로 저장되어 있는지 확인")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 티본스테이크, 초코케이크, 제로콜라, 12, 2, 3, 1",
            "타파스, 바비큐립, 아이스크림, 샴페인, 1, 2, 13, 2"
    })
    void orderHasMenuTest(String menu1, String menu2, String menu3, String menu4,
                          int quantity1, int quantity2, int quantity3, int quantity4) {
        String[] menuTypes = {menu1, menu2, menu3, menu4};
        int[] quantities = {quantity1, quantity2, quantity3, quantity4};

        Order order = new Order(menuTypes, quantities);
        Map<String, Integer> getOrders = order.getOrders();

        assertThat(getOrders.keySet().toArray()).isEqualTo(menuTypes);
    }

    @DisplayName("주문 메뉴에 디저트와 메인 메뉴 개수가 몇 개인지 반환 확인")
    @ParameterizedTest
    @CsvSource({
            "해산물파스타, 바비큐립, 크리스마스파스타, 아이스크림, 1, 2, 3, 4"
    })
    void getDessertMainQuantityTest(String menu1, String menu2, String menu3, String menu4,
                                 int quantity1, int quantity2, int quantity3, int quantity4) {
        String[] menuTypes = {menu1, menu2, menu3, menu4};
        int[] quantities = {quantity1, quantity2, quantity3, quantity4};

        Order order = new Order(menuTypes, quantities);
        Map<MenuType, Integer> findQuantities = order.getDessertAndMainQuantity();

        assertThat(findQuantities.get(MenuType.MAIN)).isEqualTo(6);
        assertThat(findQuantities.get(MenuType.DESSERT)).isEqualTo(4);
    }
}
