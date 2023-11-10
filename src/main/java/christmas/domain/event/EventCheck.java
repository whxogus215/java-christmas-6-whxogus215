package christmas.domain.event;

import christmas.domain.discount.DiscountType;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventCheck {
    private final int GIFT_MIN_PRICE = 120000;

    public List<DiscountType> checkDiscountType(int day) {
        return DiscountType.findDiscountTypeByDay(day);
    }

    public Map<Menu, Integer> checkGift(int totalPrice) {
        Map<Menu, Integer> gifts = new HashMap<>();
        gifts.put(Menu.CHAMPAGNE, totalPrice / GIFT_MIN_PRICE);
        return gifts;
    }

    public void checkBadge(int totalDiscountPrice, int giftPrice) {

    }
}
