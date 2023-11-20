package christmas.domain.event;

import java.util.Arrays;
import java.util.function.Function;

public enum EventBadge {
    NONE("없음", totalBenefitAmount -> totalBenefitAmount < 5000),
    STAR("별", totalBenefitAmount -> totalBenefitAmount >= 5000 && totalBenefitAmount < 10000),
    TREE("트리", totalBenefitAmount -> totalBenefitAmount >= 10000 && totalBenefitAmount < 20000),
    SANTA("산타", totalBenefitAmount -> totalBenefitAmount >= 20000);

    private final String name;
    private final Function<Integer, Boolean> function;

    EventBadge(String name, Function<Integer, Boolean> function) {
        this.name = name;
        this.function = function;
    }

    public static EventBadge getEventBadge(int totalBenefitAmount) {
        return Arrays.stream(values())
                .filter(badge -> badge.isEventBadge(totalBenefitAmount))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isEventBadge(int totalBenefitAmount) {
        return function.apply(totalBenefitAmount);
    }

    public String getName() {
        return name;
    }
}
