package xcarpaccio;

public class OrderCalculator {

    public static final int STANDARD_REDUCTION_1K_PLUS = 1 - 3 / 100;
    public static final int STANDARD_REDUCTION_10K_PLUS = 1 - 10 / 100;
    public static final int STANDARD_REDUCTION_50K_PLUS = 1 - 15 / 100;

    public OrderCalculator() {
    }

    static Double computeAmount(Order order) {
        if (!order.reduction.equals("STANDARD")) {
            throw new UnsupportedOperationException("The order reduction is not supported!");
        }

        double amount = order.getTotalAmount() * TaxRateHelper.taxRateFor(order.country);
        double reduction = computeReduction(amount);

        return amount * reduction;

    }

    private static double computeReduction(double amount) {
        if (50000 <= amount) {
            return STANDARD_REDUCTION_50K_PLUS;
        }
        if (10000 <= amount) {
            return STANDARD_REDUCTION_10K_PLUS;
        }
        if (amount < 1000) {
            return 1;
        }
        if (amount == 1000) {
            return STANDARD_REDUCTION_1K_PLUS;
        }
        throw new UnsupportedOperationException("We cannot yet handle reduction for an amount of " + amount);
    }
}