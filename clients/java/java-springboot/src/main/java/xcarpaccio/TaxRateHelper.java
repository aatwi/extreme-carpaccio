package xcarpaccio;

public class TaxRateHelper {

    public static double getTaxRateFor(String country) {
        if (country.equals("HU")) {
            return 1.27;
        }
        throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
    }
}
