package christmas.domain.discount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public enum DiscountType {
    /**
     * 1. 크리스마스 디데이 할인 (1~25) 2. 평일 할인 (3,4,5,6,7,10,11,12,13,14,17,18,19,20,21,24,25,26,27,28,31)
     * 3. 주말 할인 (1,2,8,9,15,16,22,23,29,30) 4. 특별 할인 (3,10,17,24,25,31) 날짜는 리스트로 관리 각 입력 값에 해당하는 경우
     * 할인 이름과 할인 금액을 반환
     */
    CHRISTMAS(
            Arrays.asList(
                    1, 2, 3, 4, 5, 6, 7, 8, 9,
                    10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                    20, 21, 22, 23, 24, 25),
            day -> DiscountPrice.DEFAULT.getPrice() + (day - 1) * DiscountPrice.UNIT.getPrice()),
    WEEKDAY(Arrays.asList(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28,
            31), day -> DiscountPrice.YEAR.getPrice()),
    WEEKEND(Arrays.asList(
            1, 2, 8, 9, 15,
            16, 22, 23, 29, 30), day -> DiscountPrice.YEAR.getPrice()),
    SPECIAL(Arrays.asList(3, 10, 17, 24, 25, 31), day -> DiscountPrice.DEFAULT.getPrice()),
    NONE(null, day -> DiscountPrice.ZERO.getPrice());

    private final List<Integer> discountDays;
    private final Function<Integer, Integer> discountFunction;

    DiscountType(List<Integer> discountDays, Function<Integer, Integer> discountFunction) {
        this.discountDays = discountDays;
        this.discountFunction = discountFunction;
    }

    public static List<DiscountType> findDiscountTypeByDay(int day) {
        List<DiscountType> types = new ArrayList<>();
        for (DiscountType type : DiscountType.values()) {
            if (type.discountDays.contains(day)) {
                types.add(type);
            }
            return types;
        }
        types.add(NONE);
        return types;
    }

    public int getDiscountPrice(int day) {
        return discountFunction.apply(day);
    }
}
