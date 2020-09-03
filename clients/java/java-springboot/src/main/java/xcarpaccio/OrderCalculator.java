package xcarpaccio;

public class OrderCalculator {
    public OrderCalculator() {
    }

    static Double computeAmount(Order order) {
        if (!order.reduction.equals("STANDARD")) {
            throw new UnsupportedOperationException("The order reduction is not supported!");
        }

        double result = order.getTotalAmount() * TaxRateHelper.getTaxRateFor(order.country);
        if (1000 <= result) {
            throw new UnsupportedOperationException("We cannot yet handle reduction for more than 1000 amount");
        }

        return result;
    }
}