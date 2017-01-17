package vn.com.vndirect.user;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.com.vndirect.customer.Customer;

@Service
public class UserServiceImpl implements UserService {
    private UserDetails userDetails;

    @Override
    public UserDetails getUser() {
        UserAuthentication userAuth =
                (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return  (UserDetails) userAuth.getDetails();
    }

    @Override
    public Customer getCustomer() {
        return getUser().getCustomer();
    }
}
