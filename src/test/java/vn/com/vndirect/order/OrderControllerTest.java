package vn.com.vndirect.order;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import vn.com.vndirect.NginxApplication;
import vn.com.vndirect.auth.TokenConsumer;
import vn.com.vndirect.auth.UtilMaker;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NginxApplication.class)
@TransactionConfiguration
@Transactional
@WebAppConfiguration
public class OrderControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private TokenConsumer consumer;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        consumer = new TokenConsumer(UtilMaker.getSecretKey());
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreateOrder() throws Exception {
        String requestCreateJson = "{ \"quantity\": 1000, \"price\":15300, \"orderType\": \"LO\"" +
                ", \"account\": 10123554, \"side\": \"NB\", \"symbol\": \"ACB\"}";

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON_UTF8).content(requestCreateJson))
                .andExpect(status().isCreated());
    }

}