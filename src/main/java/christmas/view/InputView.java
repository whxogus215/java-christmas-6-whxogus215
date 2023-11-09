package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.enums.ErrorMessage;

public class InputView {
    private final String error = "[ERROR]";
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        // 방문 날짜가 숫자 형태인지 검증
        validateInputNumber(input);
        // 방문 날짜(input)이 1이상 31이하인지 검증
    }

    private void validateInputNumber(String input) {
        if (!input.matches("^-?\\d+$")) {
            throw new IllegalArgumentException(error + ErrorMessage.NOT_NUMBER.getMessage());
        }
    }
}
