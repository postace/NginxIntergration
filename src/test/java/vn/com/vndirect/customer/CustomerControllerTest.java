package vn.com.vndirect.customer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SuppressWarnings("unused")
public class CustomerControllerTest {

    private CustomerRepository repository;
    private CustomerController controller;

    @Autowired
    public CustomerControllerTest(CustomerRepository repository, CustomerController controller) {
        this.repository = repository;
        this.controller = controller;
    }

    @Test
    public void shouldShowLoginForm() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller)
                .setViewResolvers(new StandaloneMvcTestViewResolver())
                .build();
        mockMvc.perform(get("/customer/login"))
                .andExpect(view().name("login"));
    }

    class StandaloneMvcTestViewResolver extends InternalResourceViewResolver {

        public StandaloneMvcTestViewResolver() {
            super();
        }

        @Override
        protected AbstractUrlBasedView buildView(String viewName) throws Exception {
            final InternalResourceView view = (InternalResourceView) super.buildView(viewName);
            // prevent checking for circular view paths
            view.setPreventDispatchLoop(false);
            return view;
        }
    }

}