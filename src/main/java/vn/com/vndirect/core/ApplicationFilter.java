package vn.com.vndirect.core;

import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vn.com.vndirect.user.TokenParser;
import vn.com.vndirect.user.UserAuthentication;
import vn.com.vndirect.user.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class ApplicationFilter extends GenericFilterBean {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String HEADER_BEARER = "Bearer ";

    private final TokenParser parser;

    @Autowired
    public ApplicationFilter(TokenParser parser) {
        this.parser = parser;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        saveSessionToThreadLocal(request);
        chain.doFilter(req, res);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    private void saveSessionToThreadLocal(HttpServletRequest request) {
        String header = request.getHeader(AUTH_HEADER_NAME);
        if (null != header) {
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

}
