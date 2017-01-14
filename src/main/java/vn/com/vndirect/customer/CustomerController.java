package vn.com.vndirect.customer;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vndirect.auth.TokenProducer;
import vn.com.vndirect.util.LogUtil;

import static org.apache.log4j.Level.INFO;
import static org.apache.log4j.Level.WARN;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerRepository customers;

    @Autowired
    public CustomerController(CustomerRepository customers) {
        this.customers = customers;
    }

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm(Model model) {
        model.addAttribute(new Customer());
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<String> processLogin(
                @RequestBody final Customer c) {

        Customer customer = customers.findByUsername(c.getUsername());
        HttpHeaders responseHeader = new HttpHeaders();

        if (null == customer) {
            return new ResponseEntity<String>("Incorrect username or password", responseHeader, HttpStatus.NOT_ACCEPTABLE);
        }

        TokenProducer producer = new TokenProducer("vndirect", customer.getUsername(), new String[] {"audience"}, 1159f, 1147f);
        String token = producer.token(customer);
        responseHeader.add("X-AUTH-TOKEN", token);

        return new ResponseEntity<String>("{\"token\": " + token + "}", responseHeader, HttpStatus.OK);
    }

}
