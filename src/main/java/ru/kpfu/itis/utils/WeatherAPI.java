package ru.kpfu.itis.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI {
    public static String getTemp() {
        try {
            HttpURLConnection connection = null;
            URL url = new URL(
                    "http://api.openweathermap.org/data/2.5/weather" +
                            "?id=551487" +
                            "&units=metric" +
                            "&APPID=4f970465a142c3796f341ee309c37f8e"
            );
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setDoOutput(true);

            connection.getOutputStream().write(0);
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.toString());
            return ((JSONObject) jsonObject.get("main")).get("temp").toString();
        } catch (ParseException | IOException e) {
            return null;
        }
    }
}
