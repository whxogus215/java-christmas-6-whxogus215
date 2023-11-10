package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.enums.ErrorMessage;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.internal.matchers.Or;

public class OrderTest {
    @DisplayName("메뉴판에 없는 메뉴있을 경우, 예외 발생")
    @Test
    void orderValidateNotInMenuTest() {
        String[] menuTypes = {"메로나", "등갈비", "스프라이트"};
        int[] quantities = {1, 2, 3};

        assertThatThrownBy(() -> new Order(menuTypes, quantities))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_IN_MENU.getMessage());
    }

    @DisplayName("메뉴판에 있는 메뉴있을 경우, 예외 발생X")
    @Test
    void orderValidateInMenuTest() {
        String[] menuTypes = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"};
        int[] quantities = {1, 2, 3, 4};

        assertThatCode(() -> new Order(menuTypes, quantities))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 개수가 1 미만일 때, 예외 발생")
    @Test
    void orderValidateOneLessQuantityTest() {
        String[] menuTypes = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"};
        int[] quantities = {0, 2, 3, 4};

        assertThatThrownBy(() -> new Order(menuTypes, quantities))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_IN_MENU.getMessage());
    }

    @DisplayName("메뉴 개수가 1 이상일 때, 예외 발생X")
    @Test
    void orderValidateOneMoreQuantityTest() {
        String[] menuTypes = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"};
        int[] quantities = {1, 2, 3, 4};

        assertThatCode(() -> new Order(menuTypes, quantities))
                .doesNotThrowAnyException();
    }

    @DisplayName("메뉴 개수가 중복일 때, 예외 발생")
    @Test
    void orderValidateDuplicatedMenuTest() {
        String[] menuTypes = {"양송이수프", "양송이수프", "초코케이크", "제로콜라"};
        int[] quantities = {1, 2, 3, 4};

        assertThatThrownBy(() -> new Order(menuTypes, quantities))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_IN_MENU.getMessage());
    }

    @DisplayName("메뉴가 음료만 있을 때, 예외 발생")
    @Test
    void orderValidateAllDrinkTest() {
        String[] menuTypes = {"제로콜라", "레드와인", "샴페인"};
        int[] quantities = {1, 2, 3};

        assertThatThrownBy(() -> new Order(menuTypes, quantities))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_ALL_DRINK.getMessage());
    }

    @DisplayName("메뉴가 20개 이상일 때, 예외 발생")
    @Test
    void orderValidateMaxMenuTest() {
        String[] menuTypes = {"양송이수프", "티본스테이크", "초코케이크", "제로콜라"};
        int[] quantities = {12, 2, 3, 10};

        assertThatThrownBy(() -> new Order(menuTypes, quantities))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.MAX_ORDER.getMessage());
    }

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
}
