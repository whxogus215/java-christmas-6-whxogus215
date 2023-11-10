package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void printFirstNotice(int day) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day);
    }

    public void printMenu(Map<String, Integer> orders) {
        System.out.println("<주문 메뉴>");
        for (Entry<String, Integer> orderEntry : orders.entrySet()) {
            System.out.println(orderEntry.getKey() + " " + orderEntry.getValue() + "개");
        }
    }

    public void printNotDiscountedTotalPrice(int totalPrice) {
        System.out.println("<할인 전 총주문 금액>");

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        String priceFormat = decimalFormat.format(totalPrice);
        System.out.println(priceFormat + "원");
    }

}
