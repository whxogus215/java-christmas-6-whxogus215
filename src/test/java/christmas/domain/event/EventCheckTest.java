package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventCheckTest {
    @DisplayName("증정 이벤트 물품 1개 테스트")
    @ParameterizedTest
    @ValueSource(ints = {120000, 130000, 230000, 240000, 330000, 359000})
    void eventCheckGiftTest(int totalPrice) {
        EventCheck eventCheck = new EventCheck();

        Map<Menu, Integer> gifts = eventCheck.checkGift(totalPrice);
        assertThat(gifts.get(Menu.CHAMPAGNE)).isEqualTo(1);
    }

    @DisplayName("증정 이벤트 물품 0개 테스트")
    @ParameterizedTest
    @ValueSource(ints = {110000, 100000, 30000, 40000, 119000, 59000})
    void eventCheckNotGiftTest(int totalPrice) {
        EventCheck eventCheck = new EventCheck();

        Map<Menu, Integer> gifts = eventCheck.checkGift(totalPrice);
        assertThat(gifts.get(Menu.CHAMPAGNE)).isNull();
        assertThat(gifts.get(Menu.NONE)).isEqualTo(0);
    }
}
