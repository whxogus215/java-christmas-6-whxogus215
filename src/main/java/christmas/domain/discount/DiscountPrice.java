package christmas.domain.discount;

public enum DiscountPrice {
    DEFAULT(1000),
    UNIT(100),
    YEAR(2023);

    DiscountPrice(int price) {
        this.price = price;
    }

    private final int price;

    public int getPrice() {
        return price;
    }
}
