package pl.akademiaspring.week5;

public enum Currency {
    EUR("Euro"),
    USD("US dollar"),
    CHF("Swiss franc"),
    GBP("British pound");

    Currency(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }
}
