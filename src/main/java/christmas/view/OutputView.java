package christmas.view;

import christmas.domain.menu.Menu;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printFirstNotice(int date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date);
        System.out.println();
    }

    public void printMenu(Map<String, Integer> orders) {
        System.out.println("<주문 메뉴>");
        for (Entry<String, Integer> orderEntry : orders.entrySet()) {
            System.out.println(orderEntry.getKey() + " " + orderEntry.getValue() + "개");
        }
        System.out.println();
    }

    public void printNotDiscountedTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        String priceFormat = decimalFormat.format(totalPrice);
        System.out.println(priceFormat + "원");
        System.out.println();
    }

    public void printGiftCatalog(Map<Menu, Integer> gifts) {
        System.out.println("<증정 메뉴>");

        for (Entry<Menu, Integer> giftEntry : gifts.entrySet()) {
            System.out.println(giftEntry.getKey().getName() + " " + giftEntry.getValue() + "개");
        }
        System.out.println();
    }

    public void printAllBenefits(Map<String, Integer> benefits) {
        System.out.println("<혜택 내역>");

        for (String type : benefits.keySet()) {
            System.out.println(type + ": -" + benefits.get(type) + "원");
        }
        System.out.println();
    }

    public void printAllBenefitPrice(int price) {
        System.out.println("<총혜택 금액>");
        System.out.println("-" + price + "원");
        System.out.println();
    }

    public void printDiscountedTotalPrice(int price) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(price + "원");
        System.out.println();
    }

    public void printEventBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
