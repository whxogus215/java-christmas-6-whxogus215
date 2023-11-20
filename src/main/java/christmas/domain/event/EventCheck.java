package christmas.domain.event;

import christmas.domain.discount.DiscountType;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EventCheck {
    private final int GIFT_MIN_PRICE = 120000;
    private final int GIFT_AMOUNT = 1;
    private final Menu GIFT_ITEM = Menu.CHAMPAGNE;

    public List<DiscountType> checkDiscountType(int date) {
        return DiscountType.findDiscountTypeByDate(date);
    }

    public Map<Menu, Integer> checkGift(int totalOrderAmount) {
        if (totalOrderAmount < GIFT_MIN_PRICE) {
            return Collections.singletonMap(Menu.NONE, Menu.NONE.getPrice());
        }
        return Collections.singletonMap(GIFT_ITEM, GIFT_AMOUNT);
    }

    public EventBadge checkBadge(int totalBenefitAmount) {
        return EventBadge.getEventBadge(totalBenefitAmount);
    }
}
