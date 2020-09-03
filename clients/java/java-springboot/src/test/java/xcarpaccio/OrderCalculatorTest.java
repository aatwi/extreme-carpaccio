package xcarpaccio;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static xcarpaccio.OrderCalculator.*;
import static xcarpaccio.TaxRateHelper.taxRateFor;

/**
 * Testing business methods that don't require Spring at all, using assertj
 */
public class OrderCalculatorTest {

    private final Order order = new Order();

    @Before
    public void setUp() {
        order.prices = new Double[]{500.0};
        order.quantities = new Long[]{1L};
        order.country = "DE";
        order.reduction = "STANDARD";
    }

    private double priceBeforeTax(double priceAfterTax) {
        return priceAfterTax / taxRateFor(order.country);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void it_should_throw_an_exception_for_non_valid_reduction() {
        order.reduction = "XXXX";

        computeAmount(order);
    }

    @Test
    public void it_should_apply_the_tax_rate_for_the_respective_country() {
        order.country = "HU";

        assertEquals(order.getTotalAmount() * taxRateFor("HU"), computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_equal_to_1k() {
        order.prices = new Double[]{priceBeforeTax(1000.)};

        assertEquals(1000. * STANDARD_REDUCTION_1K_PLUS,
                computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_greater_than_1k() {
        order.prices = new Double[]{2000};

        assertEquals(STANDARD_REDUCTION_THRESHOLD_50K * STANDARD_REDUCTION_50K_PLUS,
                computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_greater_to_10k() {
        order.prices = new Double[]{11000.};

        assertEquals(11000. * taxRateFor(order.country) * STANDARD_REDUCTION_10K_PLUS,
                computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_equal_to_50k() {
        order.prices = new Double[]{priceBeforeTax(STANDARD_REDUCTION_THRESHOLD_50K)};

        assertEquals(STANDARD_REDUCTION_THRESHOLD_50K * STANDARD_REDUCTION_50K_PLUS,
                computeAmount(order), 0.01);
    }

    @Test
    public void it_should_apply_standard_reduction_for_amount_greater_than_50k() {
        order.prices = new Double[]{100000.};

        assertEquals(100000 * taxRateFor(order.country) * STANDARD_REDUCTION_50K_PLUS,
                computeAmount(order), 0.01);
    }

    // TODO limit at 50000
}
