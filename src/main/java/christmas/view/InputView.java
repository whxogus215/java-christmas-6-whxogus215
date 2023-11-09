package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.ErrorMessage;

public class InputView {
    private final String error = "[ERROR] ";

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int day;
        while (true) {
            String input = Console.readLine();
            try {
                validateInputNumber(input);
                day = validateDayInRange(input);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return day;
    }

    public String[] readMenuAndCount() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input;
        while (true) {
            input = Console.readLine();
            try {
                validateInputBlank(input);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return null;
    }

    private void validateInputNumber(String input) {
        if (!input.matches("^-?\\d+$")) {
            throw new IllegalArgumentException(error + ErrorMessage.NOT_NUMBER.getMessage());
        }
    }

    private int validateDayInRange(String input) {
        int day = Integer.parseInt(input);
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(error + ErrorMessage.DAY_NOT_INRANGE.getMessage());
        }
        return day;
    }

    private void validateInputBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(error + ErrorMessage.NOT_IN_MENU.getMessage());
        }
    }

    private String getMenuInInput(String input) {
        int index = input.lastIndexOf("-");
        return input.substring(0, index - 1);
    }

    private String getCountInInput(String input) {
        int index = input.lastIndexOf("-");
        return input.substring(index + 1);
    }
}
