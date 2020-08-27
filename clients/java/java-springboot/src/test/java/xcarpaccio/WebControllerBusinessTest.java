package xcarpaccio;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Testing business methods that don't require Spring at all, using assertj
 */
public class WebControllerBusinessTest {

    @Test
    public void it_should_apply_the_tax_rate_for_the_respective_country() {
        Order order = new Order();
        order.prices = new Double[]{10.2, 20.0};
        order.quantities = new Long[]{2L, 4L};
        order.country = "HU";
        Assert.assertEquals(127.508, (new WebController()).computeAmount(order), 0.01);
    }
}
