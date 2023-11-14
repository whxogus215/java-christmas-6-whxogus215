package christmas.exception;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.utils.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputExceptionTest extends NsTest {
    @DisplayName("주문형식이 올바르지 않을 경우, 예외 발생")
    @Test
    void inputExceptionNotFormatTest() {
        assertSimpleTest(() -> {
            runException("3", "타파스-3, ");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @DisplayName("주문 형식에 공백이 있을 경우, 공백 제거")
    @Test
    void inputExceptionTrimSpaceTest() {
        assertSimpleTest(() -> {
            assertThatCode(() -> runException("3", "타파스-3 "))
                    .doesNotThrowAnyException();
        });
    }

    @DisplayName("음료만 주문할 경우, 예외 발생")
    @Test
    void inputExceptionNotAllDrinkTest() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3");
            assertThat(output()).contains(ErrorMessage.NOT_ALL_DRINK.getMessage());
        });
    }

    @DisplayName("메뉴판에 없는 메뉴있을 경우, 예외 발생")
    @Test
    void inputExceptionNotInMenuTest() {
        assertSimpleTest(() -> {
            runException("3", "타파스-3,메로나-3,등갈비-3,스프라이트-4");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @DisplayName("메뉴판에 있는 메뉴있을 경우, 예외 발생X")
    @Test
    void inputExceptionInMenuTest() {
        assertSimpleTest(() -> {
            assertThatCode(() -> runException("3", "양송이수프-3,티본스테이크-3,초코케이크-3,제로콜라-4"))
                    .doesNotThrowAnyException();
        });
    }

    @DisplayName("메뉴 개수가 1 미만일 때, 예외 발생")
    @Test
    void inputExceptionOneLessQuantityTest() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-0,티본스테이크-3,초코케이크-3,제로콜라-4");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @DisplayName("메뉴 개수가 중복일 때, 예외 발생")
    @Test
    void inputExceptionDuplicatedMenuTest() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-2,양송이수프-3,초코케이크-3,제로콜라-4");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @DisplayName("메뉴가 20개 이상일 때, 예외 발생")
    @Test
    void inputExceptionMaxMenuTest() {
        assertSimpleTest(() -> {
            runException("3", "양송이수프-2,양송이수프-13,초코케이크-3,제로콜라-4");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
