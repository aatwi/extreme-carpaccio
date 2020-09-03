package xcarpaccio;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing business methods that don't require Spring at all, using assertj
 */
public class OrderCalculatorTest {

    @Test
    public void it_should_apply_the_tax_rate_for_the_respective_country() {
        Order order = new Order();
        order.prices = new Double[]{10.2, 20.0};
        order.quantities = new Long[]{2L, 4L};
        order.country = "HU";
        order.reduction = "STANDARD";
        new WebController();
        Assert.assertEquals(127.508, OrderCalculator.computeAmount(order), 0.01);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void it_should_throw_an_exception_for_non_valid_reduction() {
        Order order = new Order();
        order.prices = new Double[]{10.2, 20.0};
        order.quantities = new Long[]{2L, 4L};
        order.country = "HU";
        order.reduction = "XXXX";

        new WebController();
        Assert.assertEquals(127.508, OrderCalculator.computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_greater_than_50k() {
        Order order = new Order();
        order.prices = new Double[]{100000.};
        order.quantities = new Long[]{1L};
        order.country = "HU";
        order.reduction = "STANDARD";
        new WebController();
        Assert.assertEquals(100000*(1 + TaxRateHelper.getTaxRateFor("HU") * (1 - 15/100)), OrderCalculator.computeAmount(order), 0.01);
    }

    // TODO limit at 50000
}
