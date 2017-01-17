package vn.com.vndirect.order;

import com.google.gson.Gson;
import org.apache.log4j.Level;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vn.com.vndirect.auth.TokenConsumer;
import vn.com.vndirect.auth.UtilMaker;
import vn.com.vndirect.customer.Customer;
import vn.com.vndirect.user.TokenParser;
import vn.com.vndirect.user.UserAuthentication;
import vn.com.vndirect.util.LogUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class OrderFilter extends GenericFilterBean {

    private static final String HTTP_POST = "POST";
    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String HEADER_BEARER = "Bearer ";
    private static final String ORDER_REQUEST_URI = "/order";

    private final TokenParser parser;

    public OrderFilter(TokenParser parser) {
        this.parser = parser;
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) rq;
        Authentication userAuth = null;

        String[] headers = request.getHeader(AUTH_HEADER_NAME).split(HEADER_BEARER);
        String token = headers[1];
        String requestURI = request.getRequestURI();

        if (requestURI.contains(ORDER_REQUEST_URI)) {
            if (HTTP_POST.equalsIgnoreCase(request.getMethod())) {

                try {
                    Gson gson = new Gson();
                    Order order = gson.fromJson(request.getReader(), Order.class);
                    userAuth = parser.getAuthentication(token);

                    SecurityContextHolder.getContext().setAuthentication(userAuth);
                    LogUtil.log(Level.INFO, this, "Customer %s is post at %s:%n%s",
                            userAuth.getName(), requestURI ,gson.toJson(order));
                } catch (InvalidJwtException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private String convertBodyRequestToString(HttpServletRequest request) {
        try {
            return request
                    .getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
