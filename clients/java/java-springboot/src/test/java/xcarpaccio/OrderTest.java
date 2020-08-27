package xcarpaccio;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void
    it_should_compute_the_total_amount_for_an_empty_order() {
        Order order = new Order();
        assertEquals(0.0, order.getTotalAmount(), 0.0);
    }

    @Test
    public void
    it_should_compute_the_total_amount_for_single_item_order_by_multiplying_the_price_with_quantity(){
        Order order = new Order();
        order.prices = new Double[]{10.2};
        order.quantities = new Long[]{2L};
        assertEquals(20.4, order.getTotalAmount(), 0.0);
    }

    @Test
    public void
    it_should_compute_the_total_amount_by_multiplying_the_price_with_quantity(){
        Order order = new Order();
        order.prices = new Double[]{10.2, 20.0};
        order.quantities = new Long[]{2L, 4L};
        assertEquals(100.4, order.getTotalAmount(), 0.0);
    }

}