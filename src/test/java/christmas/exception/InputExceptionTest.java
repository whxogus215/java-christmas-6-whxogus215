package christmas.exception;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.Application;
import christmas.utils.ErrorMessage;
import org.junit.jupiter.api.Test;

public class InputExceptionTest extends NsTest {
    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3, ");
            assertThat(output()).contains(ErrorMessage.NOT_IN_MENU.getMessage());
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
