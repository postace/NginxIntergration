package vn.com.vndirect.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Level;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DDOSFaker {

    final static String TARGET_URI = "http://mynginx.com/images/index.html";

    @Scheduled(fixedRate = 1000_000)
    private void performDDOS() {
        MultiThreadedHttpConnectionManager connectionManager =
                new MultiThreadedHttpConnectionManager();
        HttpClient client = new HttpClient(connectionManager);

        GetMethod get = new GetMethod(TARGET_URI);

        // Create 10.000 request each seconds
        for (int i = 0; i < 10_000; i++) {
            try {
                client.executeMethod(get);
                LogUtil.log(Level.INFO, this, "Response: %s", get.getResponseBodyAsString() );
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                get.releaseConnection();
            }
        }

    }
}
