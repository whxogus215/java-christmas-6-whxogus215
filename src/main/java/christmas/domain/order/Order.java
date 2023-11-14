package christmas.domain.order;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuType;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private final Map<String, Integer> orders = new LinkedHashMap<>();

    public Order(String[] menuTypes, int[] quantities) {
        for (int i = 0; i < menuTypes.length; i++) {
            orders.put(menuTypes[i], quantities[i]);
        }
    }

    public Map<String, Integer> getOrders() {
        return new LinkedHashMap<>(orders);
    }

    public Map<MenuType, Integer> getDessertAndMainQuantity() {
        Map<MenuType, Integer> catalog = new HashMap<>();
        for (String orderMenu : orders.keySet()) {
            MenuType findMenuType = Menu.findMenuByName(orderMenu).getType();
            if (findMenuType.equals(MenuType.DESSERT) || findMenuType.equals(MenuType.MAIN)) {
                catalog.merge(findMenuType, orders.get(orderMenu), Integer::sum);
            }
        }
        return catalog;
    }
}
