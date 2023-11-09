package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.ErrorMessage;

public class InputView {
    private final String error = "[ERROR]";

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int day;
        while (true) {
            String input = Console.readLine();
            try {
                // 방문 날짜가 숫자 형태인지 검증
                validateInputNumber(input);
                // 방문 날짜(input)이 1이상 31이하인지 검증
                day = validateDayInRange(input);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
        return day;
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
}
