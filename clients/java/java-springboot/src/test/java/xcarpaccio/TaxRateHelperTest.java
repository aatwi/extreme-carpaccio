package xcarpaccio;

import org.junit.Assert;
import org.junit.Test;

public class TaxRateHelperTest {

    @Test(expected = UnsupportedOperationException.class)
    public void it_should_throw_an_exception_for_unsupported_countries() {
        TaxRateHelper.taxRateFor("XX");
    }

    @Test
    public void it_should_apply_27_percent_tax_for_hungary() {
        Assert.assertEquals(1.27, TaxRateHelper.taxRateFor("HU"), 0.0);
    }

    @Test
    public void it_should_apply_20_percent_tax_for_germany() {
        Assert.assertEquals(1.20, TaxRateHelper.taxRateFor("DE"), 0.0);
    }

    @Test
    public void it_should_apply_21_percent_tax_for_poland() {
        Assert.assertEquals(1.21, TaxRateHelper.taxRateFor("PL"), 0.0);
    }
}
