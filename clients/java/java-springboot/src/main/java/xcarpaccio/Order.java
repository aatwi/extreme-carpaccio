package xcarpaccio;

import java.io.Serializable;
import java.util.Arrays;

public class Order implements Serializable {
    public Double[] prices = new Double[0];
    public Long[] quantities = new Long[0];
    public String country;
    public String reduction;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "prices=" + Arrays.toString(prices) +
                ", quantities=" + Arrays.toString(quantities) +
                ", country='" + country + '\'' +
                ", reduction='" + reduction + '\'' +
                '}';
    }

    public Double getTotalAmount() {


        if(prices.length != quantities.length) {
            throw new UnsupportedOperationException("Dimension of quantity should be equal to that of the prices");
        }

        double amount = 0.0;
        for (int i = 0; i < prices.length; i++) {
            amount += prices[i] * quantities[i];
        }
        return amount;
    }

    //todo: Test this
    private boolean isEmptyOrder() {
        if (prices == null || quantities == null) {
            return true;
        }
        return prices.length == 0 || quantities.length == 0;
    }
}