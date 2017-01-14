package vn.com.vndirect.web;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

public class CustomerControllerTest {
    private CustomerController controller;

    @Before
    public void setup() {
        controller = new CustomerController();
    }

    @Test
    public void shouldShowLoginForm() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/customer/login"))
                .andExpect(view().name("login"));
    }

}