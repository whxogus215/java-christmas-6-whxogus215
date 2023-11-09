package christmas.enums;

public enum ErrorMessage {
    NOT_DAY("날짜를 입력해주세요"),
    NOT_NUMBER("숫자를 입력해주세요."),
    DAY_NOT_INRANGE("날짜는 1부터 31 사이의 숫자여야 합니다.");
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
