package christmas.domain.menu;

public enum MenuType {
    Appetizer("애피타이저"),
    Main("메인"),
    Dessert("디저트"),
    Drink("음료");

    private final String menuType;

    MenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuType() {
        return menuType;
    }
}
