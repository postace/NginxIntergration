package vn.com.vndirect.auth;

import org.apache.log4j.Level;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vn.com.vndirect.customer.Customer;
import vn.com.vndirect.util.LogUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderFilter extends GenericFilterBean {

    private static final String HTTP_POST = "POST";
    private static final String AUTH_HEADER_NAME = "Authorization";

    private TokenConsumer consumer = new TokenConsumer("vndirect", UtilMaker.getSecretKey());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest currentRequest = (HttpServletRequest) request;

        String header = ((HttpServletRequest) request).getHeader(AUTH_HEADER_NAME);

        if (HTTP_POST.equalsIgnoreCase(currentRequest.getMethod())) {

            try {
                String requestBody = currentRequest
                        .getReader()
                        .lines()
                        .collect(Collectors.joining(System.lineSeparator()));
                Customer customer = consumer.consume(header);

                LogUtil.log(Level.INFO, this, "Customer %s is create order:%n%s", customer.getUsername(), requestBody);
            } catch (InvalidJwtException e) {
                e.printStackTrace();
            }
        }
    }

    private JSONObject convertRequestToJson(HttpServletRequest request) {
        JSONObject reqJson = new JSONObject();
        Map<String, String[]> params = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String v[] = entry.getValue();
            Object o = (v.length == 1) ? v[0] : v;
            reqJson.put(entry.getKey(), o);
        }
        return reqJson;
    }
}
