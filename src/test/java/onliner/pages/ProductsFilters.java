package onliner.pages;

public enum ProductsFilters {
    MANUFACTURER("Производитель"),
    PRICETO("до"),
    RESOLUTION("Разрешение"),
    DIAGONALFROM("Диагональ"),
    DIAGONALTO("Диагональ");

    private final String value;

    ProductsFilters(final String valueToSet) {
        value = valueToSet;
    }

    public String toString() {
        return value;
    }
}