package example.com.appandroid.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IPConfig {
    private static int build = 0;
    private static String urlPre;
    private static List<String> urlMap = Arrays.asList(
            "http://172.31.60.66:8184"
    );

    static String getUrlPre() {
        return urlMap.get(build);
    }
}
