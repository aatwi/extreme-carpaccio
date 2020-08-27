package xcarpaccio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class WebController {

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Amount answerQuote(@RequestBody Order order) {
        System.out.println("Order received: " + order.toString());
        try {
            if (order.prices.length == 0)
                return new Amount(computeAmount(order));

            if (order.country.equals("HU")) {
                Amount amount = new Amount(computeAmount(order));
                if (amount.total < 1000) {
                    return amount;
                }
            }

        } catch (Exception exp) {
            System.err.println("An Error Occurred while processing the order: " + order.toString());
            exp.printStackTrace();
            // Throw a 404 if you don't want to respond to an order, without penalty
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "cannot answer");
    }

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public void logFeedback(@RequestBody FeedbackMessage message) {
        System.out.println("feedback received: " + message.toString());
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        System.out.println("ping received");
        return "pong";
    }

    Double computeAmount(Order order) {
        return order.getTotalAmount() * TaxRateHelper.getTaxRateFor(order.country);

    }
}