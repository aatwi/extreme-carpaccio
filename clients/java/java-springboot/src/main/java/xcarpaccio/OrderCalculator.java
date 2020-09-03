package xcarpaccio;

public class OrderCalculator {
    public OrderCalculator() {
    }

    static Double computeAmount(Order order) {
        if (!order.reduction.equals("STANDARD")) {
            throw new UnsupportedOperationException("The order reduction is not supported!");
        }

        double amount = order.getTotalAmount() * TaxRateHelper.getTaxRateFor(order.country);
        if (amount < 1000) {
            return amount;
        }
        if (50000 <= amount) {
            return amount * (1 - 15/100);
        }
        throw new UnsupportedOperationException("We cannot yet handle reduction for an amount of " + amount);

    }
}