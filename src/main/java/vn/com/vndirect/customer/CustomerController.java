package vn.com.vndirect.customer;

import org.apache.log4j.Level;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.vndirect.util.LogUtil;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm(Model model) {
        model.addAttribute(new Customer());
        return "login";
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<String> processLogin(
                @RequestBody final Customer customer) {

        LogUtil.log(Level.INFO, this, "Receive username: %s, email: %s", customer.getUsername(), customer.getEmail() );
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("X-AUTH-TOKEN", "Your token here!");
        return new ResponseEntity<String>("Accepted", responseHeader, HttpStatus.ACCEPTED);
    }

}
