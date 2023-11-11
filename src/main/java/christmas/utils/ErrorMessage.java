package christmas.utils;

public enum ErrorMessage {
    ERROR_CODE("[ERROR] "),
    DAY_NOT_INRANGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_IN_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_ALL_DRINK("음료만 주문할 수 없습니다. 다시 주문해주세요."),
    MAX_ORDER("메뉴는 최대 20개까지만 주문할 수 있습니다. 다시 주문해주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
