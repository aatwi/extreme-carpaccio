package xcarpaccio;

import java.util.HashMap;
import java.util.Map;

final class TaxRateHelper {

    private TaxRateHelper() {}

    static final Map<String, Double> taxPerCountry = new HashMap<String, Double>(){{
        put("HU", 1.27);
        put("DE", 1.20);
        put("PL", 1.21);
    }};

    public static double taxRateFor(String country) {
        try {
            return taxPerCountry.get(country);

        } catch(NullPointerException ex) {
            throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
        }
    }
}
