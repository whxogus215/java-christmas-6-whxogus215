package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.utils.Regex;
import christmas.utils.ErrorMessage;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        validateInputNumber(input);
        validateDateInRange(input);
        return Integer.parseInt(input);
    }

    public String readMenuAndCount() {
        System.out.println(
                "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        validateInputBlank(input);
        return input;
    }

    private void validateInputNumber(String input) {
        if (!input.matches(Regex.INTEGER.getRegex())) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_CODE.getMessage()
                    + ErrorMessage.DAY_NOT_INRANGE.getMessage());
        }
    }

    private void validateDateInRange(String input) {
        int date = Integer.parseInt(input);
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_CODE.getMessage()
                    + ErrorMessage.DAY_NOT_INRANGE.getMessage());
        }
    }

    public static void validateInputBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.NOT_IN_MENU.getMessage());
        }
    }
}
