package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.enums.ErrorMessage;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<MenuType, Integer> orders = new HashMap<>();

    public Order(String[] menuTypes, int[] quantities) {
        validateMenuType(menuTypes);
    }

    private void validateMenuType(String[] menuTypes) {
        for (String menuType : menuTypes) {
            if (Menu.isNotInMenu(menuType)) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage() +
                        ErrorMessage.NOT_IN_MENU.getMessage());
            }
        }
    }

    // Integer(Quantity)에 대한 검증 로직
}
