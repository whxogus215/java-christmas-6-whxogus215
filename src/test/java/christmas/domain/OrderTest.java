package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.enums.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
