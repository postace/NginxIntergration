package vn.com.vndirect.util;

import org.apache.log4j.Level;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ComparePerformance {
    private static final String NGINX_VALIDATE_URL = "http://10.26.0.113/";
    private static final String JAVA_VALIDATE_URL = "http://10.26.0.113/token-java";
    private RestTemplate restTemplate;

    public ComparePerformance() {
        restTemplate = new RestTemplate();
    }

    public long getTimeValidateNginx() {
        return getTimeValidate(NGINX_VALIDATE_URL);
    }

    public long getTimeValidateJava() {
        return getTimeValidate(JAVA_VALIDATE_URL);
    }

    private long getTimeValidate(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", getToken());
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        long startTime = System.nanoTime();
        for (int i = 0; i < 10; i++) {
            restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private String getToken() {
        return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ2bmRpcmVjdCIsInN1YiI6ImpvaG5kb2UiLCJhdWQiOiJhdWRpZW5jZSIsImp0aSI6IjRkaHNBYk9NU1VhbkpFSnFSMmx5Q2ciLCJpYXQiOjE0ODQ2Mzk4NTYsImV4cCI6MTQ4NDY2ODY1NiwibmJmIjoxNDg0NjM5NzM2LCJjdXN0b21lcklkIjoiMTExMSIsInVzZXJOYW1lIjoiam9obmRvZSIsInVzZXJJZCI6IjExMSIsImN1c3RvbWVyTmFtZSI6IkpvaG5Eb2UiLCJlbWFpbCI6ImpvaG5kb2VAZ21haWwuY29tIn0.IG1LSGy7oQW2_df_Q-zmX0ciZzJ5ARZdK50MR1cDj7Y";
    }

    public static void main(String[] args) {
        ComparePerformance cp = new ComparePerformance();
        long timeNginx = cp.getTimeValidateNginx() / 1_000_000;
        long timeJava = cp.getTimeValidateJava() / 1_000_000;

        LogUtil.log(Level.INFO, cp.getClass(),
                "%nNginx validate time: %dms%n" +
                        "Java validate time: %dms", timeNginx, timeJava);
    }

}
