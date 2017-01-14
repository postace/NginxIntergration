package vn.com.vndirect.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.vndirect.model.Customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping(value = "/login", method = GET)
    public String showLoginForm(Model model) {
        model.addAttribute(new Customer());
        return "login";
    }

}
