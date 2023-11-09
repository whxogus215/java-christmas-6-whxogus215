package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import christmas.enums.ErrorMessage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order {
    private Map<MenuType, Integer> orders = new HashMap<>();

    public Order(String[] menuTypes, int[] quantities) {
        validateMenuType(menuTypes);
        validateAllDrinkInMenu(menuTypes);
        validateQuantity(quantities);
    }

    private void validateMenuType(String[] menuTypes) {
        validateIsInMenu(menuTypes);
        validateDuplicateInMenu(menuTypes);
    }

    private void validateIsInMenu(String[] menuTypes) {
        for (String menuType : menuTypes) {
            if (Menu.isNotInMenu(menuType)) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.NOT_IN_MENU.getMessage());
            }
        }
    }

    private void validateDuplicateInMenu(String[] menuTypes) {
        Set<String> menus = new HashSet<>(List.of(menuTypes));
        if (menus.size() != menuTypes.length) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.NOT_IN_MENU.getMessage());
        }
    }

    private void validateAllDrinkInMenu(String[] menuTypes) {
        List<String> drinkMenu = Menu.getDrinkMenu();
        List<String> menus = List.of(menuTypes);
        if (drinkMenu.equals(menus)) {
            throw new IllegalArgumentException(
                    ErrorMessage.ERROR_CODE.getMessage() + ErrorMessage.NOT_ALL_DRINK.getMessage());
        }
    }

    private void validateQuantity(int[] quantities) {
        for (int quantity : quantities) {
            if (quantity < 1) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.NOT_IN_MENU.getMessage());
            }
        }
    }
}
