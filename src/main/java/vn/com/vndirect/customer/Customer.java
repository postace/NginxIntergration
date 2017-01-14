package vn.com.vndirect.customer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@SuppressWarnings("serial")
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String customerId;
    private String customerName;
    private String userId;
    private String username;
    private String password;
    @Transient
    private List<String> roles;
    private String email;

    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public Customer() {
    }

    public Customer(String customerId, String customerName, String userId, String username, String password, List<String> roles, String email) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.userId = userId;
        this.username = username;
        setPassword(password);
        this.roles = roles;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String customer = "Customer [username = %s, userId= %s," +
                "customerId = %s, customerName = %s, roles = %s, email = %s]";
        return String.format(customer, getUsername(), getUserId(),
                getCustomerId(), getCustomerName(), getRoles(), getEmail());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((roles == null) ? 0 : roles.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Customer other = (Customer) obj;
        if (customerId == null) {
            if (other.getCustomerId() != null)
                return false;
        } else if (!customerId.equals(other.getCustomerId())) {
            return false;
        }
        if (customerName == null) {
            if (other.getCustomerName() != null)
                return false;
        } else if (!customerName.equals(other.getCustomerName())) {
            return false;
        }
        if (userId == null) {
            if (other.getUserId() != null)
                return false;
        } else if (!userId.equals(other.getUserId())) {
            return false;
        }
        if (username == null) {
            if (other.getUsername() != null)
                return false;
        } else if (!username.equals(other.getUsername())) {
            return false;
        }
        if (roles == null) {
            if (other.getRoles() != null)
                return false;
        } else if (!roles.equals(other.getRoles())) {
            return false;
        }
        if (email == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!email.equals(other.getEmail())) {
            return false;
        }
        return true;
    }
}
