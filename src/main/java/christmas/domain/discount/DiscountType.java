package christmas.domain.discount;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DiscountType {
    CHRISTMAS(
            "크리스마스 디데이 할인", Arrays.asList(
                    1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                    20, 21, 22, 23, 24, 25),
            day -> DiscountPrice.DEFAULT.getPrice() + (day - 1) * DiscountPrice.UNIT.getPrice()),
    WEEKDAY("평일 할인", Arrays.asList(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31), day -> DiscountPrice.YEAR.getPrice()),
    WEEKEND("주말 할인", Arrays.asList(
            1, 2, 8, 9, 15,
            16, 22, 23, 29, 30), day -> DiscountPrice.YEAR.getPrice()),
    SPECIAL("특별 할인", Arrays.asList(3, 10, 17, 24, 25, 31),
            day -> DiscountPrice.DEFAULT.getPrice()),
    NONE("없음", null, day -> DiscountPrice.ZERO.getPrice());

    private final String discountName;
    private final List<Integer> discountDays;
    private final Function<Integer, Integer> discountFunction;

    DiscountType(String discountName,
                 List<Integer> discountDays,
                 Function<Integer, Integer> discountFunction) {
        this.discountName = discountName;
        this.discountDays = discountDays;
        this.discountFunction = discountFunction;
    }

    public static List<DiscountType> findDiscountTypeByDay(int day) {
        List<DiscountType> types = Arrays.stream(values())
                .filter(type -> type.discountDays != null && type.discountDays.contains(day))
                .collect(Collectors.toList());

        if (types.isEmpty()) {
            types.add(NONE);
        }
        return types;
    }

    public int getDiscountPrice(int day) {
        return discountFunction.apply(day);
    }

    public String getDiscountName() {
        return discountName;
    }
}
