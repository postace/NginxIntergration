package vn.com.vndirect.order;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.com.vndirect.user.UserAuthentication;
import vn.com.vndirect.user.UserService;
import vn.com.vndirect.util.LogUtil;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private UserService userService;

    @Autowired
    public OrderController(UserService service) {
        this.userService = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getOrder() {
        return "Success get order!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        UserAuthentication userAuth = (UserAuthentication)
                        SecurityContextHolder.getContext().getAuthentication();
        LogUtil.log(Level.INFO, this, "Customer %s created order: %n%s",
                userAuth.getUser().getUsername(),
                order.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
