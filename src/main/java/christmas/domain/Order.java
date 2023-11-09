package christmas.domain;

import static christmas.enums.ErrorMessage.ERROR_CODE;
import static christmas.enums.ErrorMessage.NOT_IN_MENU;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
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
                        ERROR_CODE.getMessage() + NOT_IN_MENU.getMessage());
            }
        }
    }

    // Integer(Quantity)에 대한 검증 로직
}
