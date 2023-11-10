package christmas.domain.discount;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DiscountTypeTest {
    private final int DEFAULT = DiscountPrice.DEFAULT.getPrice();
    private final int UNIT = DiscountPrice.UNIT.getPrice();
    private final int YEAR = DiscountPrice.YEAR.getPrice();

    @DisplayName("크리스마스 디데이 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 12, 23, 25})
    void discountTypeChristTest(int day) {
        List<DiscountType> types = DiscountType.findDiscountTypeByDay(day);

        assertThat(DiscountType.CHRISTMAS).isIn(types);
        assertThat(DiscountType.CHRISTMAS.getDiscountPrice(day))
                .isEqualTo(DEFAULT + (day - 1) * UNIT);
    }

    @DisplayName("평일 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {4, 12, 20, 28})
    void discountTypeWeekDayTest(int day) {
        List<DiscountType> types = DiscountType.findDiscountTypeByDay(day);

        assertThat(DiscountType.WEEKDAY).isIn(types);
        assertThat(DiscountType.WEEKDAY.getDiscountPrice(day))
                .isEqualTo(YEAR);
    }

    @DisplayName("주말 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {8, 9, 15, 23})
    void discountTypeWeekendTest(int day) {
        List<DiscountType> types = DiscountType.findDiscountTypeByDay(day);

        assertThat(DiscountType.WEEKEND).isIn(types);
        assertThat(DiscountType.WEEKEND.getDiscountPrice(day))
                .isEqualTo(YEAR);
    }

    @DisplayName("특별 할인 테스트")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24})
    void discountTypeSpecialTest(int day) {
        List<DiscountType> types = DiscountType.findDiscountTypeByDay(day);

        assertThat(DiscountType.SPECIAL).isIn(types);
        assertThat(DiscountType.SPECIAL.getDiscountPrice(day))
                .isEqualTo(DEFAULT);
    }
}
