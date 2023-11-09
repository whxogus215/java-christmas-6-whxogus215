package christmas.domain.menu;

public enum Dessert implements MenuType {
    CHOCO_CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000);

    private final String name;
    private final int price;

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
