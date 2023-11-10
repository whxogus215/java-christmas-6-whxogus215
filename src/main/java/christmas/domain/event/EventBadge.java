package christmas.domain.event;

import java.util.Arrays;
import java.util.function.Function;

public enum EventBadge {
    NONE("없음", totalBenefit -> totalBenefit < 5000),
    STAR("별", totalBenefit -> totalBenefit >= 5000 && totalBenefit < 10000),
    TREE("트리", totalBenefit -> totalBenefit >= 10000 && totalBenefit < 20000),
    SANTA("산타", totalBenefit -> totalBenefit >= 20000);

    private final String name;
    private final Function<Integer, Boolean> function;

    EventBadge(String name, Function<Integer, Boolean> function) {
        this.name = name;
        this.function = function;
    }

    public static EventBadge getEventBadge(int totalBenefit) {
        return Arrays.stream(values())
                .filter(badge -> badge.isEventBadge(totalBenefit))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isEventBadge(int totalBenefit) {
        return function.apply(totalBenefit);
    }

    public String getName() {
        return name;
    }
}
