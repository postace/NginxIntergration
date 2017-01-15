package vn.com.vndirect.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CustomerRepository customerRepo;

    @Autowired
    public DatabaseLoader(CustomerRepository customers) {
        this.customerRepo = customers;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> adminRole = new ArrayList<>();
        adminRole.add("ROLE_ADMIN");
        adminRole.add("ROLE_USER");
        List<String> normalRole = new ArrayList<>();
        normalRole.add("ROLE_USER");
        List<Customer> customers = Arrays.asList(
                new Customer("1111", "JohnDoe", "111", "johndoe", "123456", normalRole, "johndoe@gmail.com"),
                new Customer("1112", "Veronica Rose", "112", "veronica", "abcdefgh", normalRole, "vero@outlook.com"),
                new Customer("1113", "Gerry Lynch", "113", "gelynch", "idontknow", normalRole, "gerry.lynch@yahoo.com"),
                new Customer("1114", "Matt Crist", "114", "matt.crist", "somepasswd", normalRole, "matt.crist@gmail.com"),
                new Customer("1115", "Marie Barrows", "115", "marrieb", "lov3y0u", adminRole, "marrie.barrows@example.com")
        );

        customerRepo.save(customers);
    }
}
