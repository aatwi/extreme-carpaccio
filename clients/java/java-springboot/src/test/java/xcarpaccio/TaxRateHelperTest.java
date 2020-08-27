package xcarpaccio;

import org.junit.Assert;
import org.junit.Test;

public class TaxRateHelperTest {

    @Test(expected = UnsupportedOperationException.class)
    public void it_should_throw_an_exception_for_unsupported_countries() {
        TaxRateHelper.getTaxRateFor("XX");
    }

    @Test
    public void it_should_apply_27_percent_tax_for_hungary() {
        Assert.assertEquals(1.27, TaxRateHelper.getTaxRateFor("HU"), 0.0);
    }
}
