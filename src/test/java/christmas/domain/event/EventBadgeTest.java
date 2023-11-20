package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EventBadgeTest {
    @DisplayName("별 배지 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {5000, 8000, 9900})
    void eventBadgeStarTest(int totalBenefit) {
        EventBadge badge = EventBadge.getEventBadge(totalBenefit);
        assertThat(badge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("트리 배지 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {10000, 13000, 19900})
    void eventBadgeTreeTest(int totalBenefit) {
        EventBadge badge = EventBadge.getEventBadge(totalBenefit);
        assertThat(badge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("산타 배지 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {20000, 23000, 59900})
    void eventBadgeSantaTest(int totalBenefit) {
        EventBadge badge = EventBadge.getEventBadge(totalBenefit);
        assertThat(badge).isEqualTo(EventBadge.SANTA);
    }

    @DisplayName("없음 배지 반환 테스트")
    @ParameterizedTest
    @ValueSource(ints = {2000, 3000, 4900})
    void eventBadgeNoneTest(int totalBenefit) {
        EventBadge badge = EventBadge.getEventBadge(totalBenefit);
        assertThat(badge).isEqualTo(EventBadge.NONE);
    }
}
