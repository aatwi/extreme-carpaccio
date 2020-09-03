package xcarpaccio;

import java.util.HashMap;
import java.util.Map;

public final class TaxRateHelper {

    static final Map<String, Double> taxPerCountry = new HashMap<String, Double>(){{
        put("HU", 1.27);
        put("DE", 1.20);
        put("PL", 1.21);
    }};

    public static double taxRateFor(String country) {

        if (country.equals("HU")) {
            return 1.27;
        }
        if (country.equals("DE")) {
            return 1.20;
        }
        if (country.equals("PL")) {
            return 1.21;
        }
        throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
    }
}
