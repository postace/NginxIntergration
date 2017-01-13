package vn.com.vndirect.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class SimpleController {

    @GetMapping
    public Map<Long, String> hello() {
        HashMap<Long, String> map = new HashMap<>();
        map.put(1L, "Hello");
        map.put(2L, "Xin chao");
        return map;
    }
}
