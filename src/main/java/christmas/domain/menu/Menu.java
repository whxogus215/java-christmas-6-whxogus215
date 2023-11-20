package christmas.domain.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuType.APPETIZER, "타파스", 5500),
    SALAD(MenuType.APPETIZER, "시저샐러드", 8000),

    STEAK(MenuType.MAIN, "티본스테이크", 55000),
    LIP(MenuType.MAIN, "바비큐립", 54000),
    FISH_PASTA(MenuType.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25000),

    CHOCO_CAKE(MenuType.DESSERT, "초코케이크", 15000),
    ICECREAM(MenuType.DESSERT, "아이스크림", 5000),

    ZERO_COKE(MenuType.DRINK, "제로콜라", 3000),
    RED_WINE(MenuType.DRINK, "레드와인", 60000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25000),

    NONE(MenuType.GIFT, "없음", 0);

    private final MenuType type;
    private final String name;
    private final int price;

    Menu(MenuType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public MenuType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static boolean isNotInMenu(String name) {
        return Arrays.stream(values())
                .noneMatch(menu -> menu.getName().equals(name));
    }

    public static List<String> getDrinkMenu() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getType().equals(MenuType.DRINK))
                .map(Menu::getName)
                .collect(Collectors.toList());
    }

    public static Menu findMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName))
                .findAny()
                .get();
    }
}
