package xcarpaccio;

public class OrderCalculator {

    public static final int STANDARD_REDUCTION_50K_PLUS = 1 - 15 / 100;

    public OrderCalculator() {
    }

    static Double computeAmount(Order order) {
        if (!order.reduction.equals("STANDARD")) {
            throw new UnsupportedOperationException("The order reduction is not supported!");
        }

        double amount = order.getTotalAmount() * TaxRateHelper.taxRateFor(order.country);
        if (amount < 1000) {
            return amount;
        }
        if (50000 <= amount) {
            return amount * STANDARD_REDUCTION_50K_PLUS;
        }
        throw new UnsupportedOperationException("We cannot yet handle reduction for an amount of " + amount);

    }
}