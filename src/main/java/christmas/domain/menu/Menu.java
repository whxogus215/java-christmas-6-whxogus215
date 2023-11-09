package christmas.domain.menu;

public enum Menu {
    MUSHROOM_SOUP(MenuType.Appetizer, "양송이수프", 6000),
    TAPAS(MenuType.Appetizer, "타파스", 5500),
    SALAD(MenuType.Appetizer, "시저샐러드", 8000),

    STEAK(MenuType.Main, "티본스테이크", 55000),
    LIP(MenuType.Main, "바비큐립", 54000),
    FISH_PASTA(MenuType.Main, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.Main, "크리스마스파스타", 25000),

    CHOCO_CAKE(MenuType.Dessert, "초코케이크", 15000),
    ICECREAM(MenuType.Dessert, "아이스크림", 5000),

    ZERO_COKE(MenuType.Drink, "제로콜라", 3000),
    RED_WINE(MenuType.Drink, "레드와인", 60000),
    CHAMPAGNE(MenuType.Drink, "샴페인", 25000);

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
}
