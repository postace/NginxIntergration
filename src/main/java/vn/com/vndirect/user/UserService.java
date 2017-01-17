package vn.com.vndirect.user;

import vn.com.vndirect.customer.Customer;

public interface UserService {

    UserDetails getUser();

    Customer getCustomer();
}
