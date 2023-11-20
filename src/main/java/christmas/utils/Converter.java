package christmas.utils;

import christmas.view.InputView;

public class Converter {
    public static String[] getMenuNames(String input) {
        String[] orders = splitOrderByComma(input);
        return convertMenusByHypen(orders);
    }

    public static int[] getQuantities(String input) {
        String[] orders = splitOrderByComma(input);
        return convertCountByHypen(orders);
    }

    private static String[] splitOrderByComma(String input) {
        String[] splitOrders = input.split(",");
        for (String splitedOrder : splitOrders) {
            InputView.validateInputBlank(splitedOrder);
        }
        return removeElementSpace(splitOrders);
    }

    private static String[] removeElementSpace(String[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = input[i].trim();
        }
        return input;
    }

    private static String[] convertMenusByHypen(String[] orders) {
        String[] menus = new String[orders.length];
        for (int i = 0; i < orders.length; i++) {
            int index = orders[i].lastIndexOf("-");
            String menu = orders[i].substring(0, index);
            if (!menu.matches(Regex.ONLY_STRING.getRegex())) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.REPEAT_ORDER.getMessage());
            }
            menus[i] = menu;
        }
        return menus;
    }

    private static int[] convertCountByHypen(String[] orders) {
        int[] quantities = new int[orders.length];
        for (int i = 0; i < orders.length; i++) {
            int index = orders[i].lastIndexOf("-");
            String count = orders[i].substring(index + 1);
            if (!count.matches(Regex.NOT_EMPTY_NUMBER.getRegex())) {
                throw new IllegalArgumentException(
                        ErrorMessage.ERROR_CODE.getMessage()
                                + ErrorMessage.REPEAT_ORDER.getMessage());
            }
            quantities[i] = Integer.parseInt(count);
        }
        return quantities;
    }
}
