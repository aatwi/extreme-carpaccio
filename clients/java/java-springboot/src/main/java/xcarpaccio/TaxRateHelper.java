package xcarpaccio;

public class TaxRateHelper {

    public static double taxRateFor(String country) {
        if (country.equals("HU")) {
            return 1.27;
        }
        throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
    }
}
