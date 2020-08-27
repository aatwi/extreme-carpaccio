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
            Amount amount = computeAmount(order);

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

    Amount computeAmount(Order order) {
        if (!order.reduction.equals("STANDARD")) {
            throw new UnsupportedOperationException("The order reduction is not supported!");
        }

        double result = order.getTotalAmount() * TaxRateHelper.getTaxRateFor(order.country);
        if (1000 <= result) {
            throw new UnsupportedOperationException("We cannot yet handle reduction for more than 1000 amount");
        }

        return new Amount(result);
    }
}