package EnergySources.Connector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Request the current weather from a weather api
 */
public class WeatherConnector {
    private static final String APIKEY = "e0163b0dc53182f484cfec672c2e3864"; //"e0163b0dc53182f484cfec672c2e3864";
    private static final String APIPATH = "https://api.openweathermap.org/data/2.5/weather?";

    public static Map<String, Double> performRequest() {
        OkHttpClient client = new OkHttpClient();

        String url = null;
        try {
            url = APIPATH + getParamsString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder().url(url).build();

        String responseJson = "";
        try (Response response = client.newCall(request).execute()) {
            responseJson = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseWeatherData(responseJson);
    }


    public static String getParamsString() throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        Map<String, String> params = setupParameters();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }

    private static Map<String, String> setupParameters() {
        Map<String, String> params = new HashMap<>();

        params.put("APPID", APIKEY);
        params.put("q", "Hagenberg");
        params.put("units", "metric");

        return params;
    }

    private static Map<String, Double> parseWeatherData(String data){
        Map<String, Double> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        if(data == ""){
            return map;
        }

        try {
            JsonNode rootNode = mapper.readTree(data);
            JsonNode mainNode = rootNode.get("main");
            JsonNode sysNode = rootNode.get("sys");
            JsonNode cloudNode = rootNode.get("clouds");
            double temperature = mainNode.get("temp").asDouble();

            map.put("temp", temperature);

            long sunrise = sysNode.get("sunrise").asLong() * 1000;
            long sunset = sysNode.get("sunset").asLong() * 1000;

            double difference = (double) sunset-sunrise;
            map.put("dayLight", difference);
            map.put("cloud", cloudNode.get("all").asDouble());

        } catch (IOException e) {
            e.printStackTrace();
            return map;
        }


        return map;
    }
}
