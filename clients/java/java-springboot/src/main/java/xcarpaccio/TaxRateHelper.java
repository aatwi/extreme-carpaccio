package xcarpaccio;

import java.util.HashMap;
import java.util.Map;

final class TaxRateHelper {

    private TaxRateHelper() {}

    static final Map<String, Double> taxPerCountry = new HashMap<String, Double>(){{
        put("DE", 1.20);
        put("UK", 1.21);
        put("FR", 1.20);
        put("IT", 1.25);
        put("ES", 1.19);
        put("PL", 1.21);
        put("RO", 1.20);
        put("NL", 1.20);
        put("BE", 1.24);
        put("EL", 1.20);
        put("CZ", 1.19);
        put("PT", 1.23);
        put("HU", 1.27);
        put("SE", 1.23);
        put("AT", 1.22);
        put("BG", 1.21);
        put("DK", 1.21);
        put("FI", 1.17);
        put("SK", 1.18);
        put("IE", 1.21);
        put("HR", 1.23);
        put("LT", 1.23);
        put("SI", 1.24);
        put("LV", 1.20);
        put("EE", 1.22);
        put("CY", 1.21);
        put("LU", 1.25);
        put("MT", 1.20);
    }};

    public static double taxRateFor(String country) {
        try {
            return taxPerCountry.get(country);

        } catch(NullPointerException ex) {
            throw new UnsupportedOperationException("Tax calculation for: " + country + " is not supported!");
        }
    }
}
