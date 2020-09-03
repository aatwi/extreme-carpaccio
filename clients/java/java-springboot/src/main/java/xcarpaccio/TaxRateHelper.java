package xcarpaccio;

public class TaxRateHelper {

    public static double taxRateFor(String country) {
        if (country.equals("HU")) {
            return 1.27;
        }
        if (country.equals("DE")) {
            return 1.20;
        }
        if (country.equals("PL")) {
            return 1.21;
        }        throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
    }
}
