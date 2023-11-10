package christmas;

public enum Regex {
    INTEGER("정수 값이어야 함", "^-?\\d+$"),
    NOT_EMPTY_NUMBER("공백을 허용하지 않는 숫자이어야 함", "^\\d+$"),
    ONLY_STRING("반드시 문자이어야 함", "^[가-힣]+$");

    private final String detail;
    private final String regex;

    Regex(String detail, String regex) {
        this.detail = detail;
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
