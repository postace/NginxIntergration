package vn.com.vndirect.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return new ResponseEntity<Order>(HttpStatus.CREATED);
    }

}
