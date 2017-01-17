package vn.com.vndirect.order;

import com.google.gson.Gson;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vn.com.vndirect.user.TokenParser;
import vn.com.vndirect.user.UserAuthentication;
import vn.com.vndirect.user.UserService;
import vn.com.vndirect.util.LogUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.apache.log4j.Level.INFO;

@Component
public class OrderFilter extends GenericFilterBean {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String HEADER_BEARER = "Bearer ";

    private final TokenParser parser;
    private UserService userService;

    @Autowired
    public OrderFilter(TokenParser parser, UserService userService) {
        this.parser = parser;
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) rq;
        saveSessionToThreadLocal(request);

        String requestURI = request.getRequestURI();

        Gson gson = new Gson();
        Order order = gson.fromJson(request.getReader(), Order.class);

        LogUtil.log(INFO, this, "Customer %s is %s at %s:%n%s",
                userService.getCustomer().getUsername(),
                request.getMethod(),
                requestURI,
                gson.toJson(order));

    }

    private void saveSessionToThreadLocal(HttpServletRequest request) {
        String[] headers = request.getHeader(AUTH_HEADER_NAME).split(HEADER_BEARER);
        String token = headers[1];
        try {
            UserAuthentication userAuth
                    = (UserAuthentication) parser.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(userAuth);
        } catch (InvalidJwtException e) {
            e.printStackTrace();
        }
    }

}
