package christmas.domain.menu;

public enum Main {
    STEAK("티본스테이크", 55000),
    LIP("바비큐립", 54000),
    FISH_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private final String name;
    private final int price;

    Main(String name, int price) {
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
