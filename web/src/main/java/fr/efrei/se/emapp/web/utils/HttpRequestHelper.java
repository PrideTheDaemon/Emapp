package fr.efrei.se.emapp.web.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fr.efrei.se.emapp.common.model.EmployeeTranscript;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static fr.efrei.se.emapp.web.utils.HttpMethod.GET;
import static fr.efrei.se.emapp.web.utils.HttpMethod.PUT;

public class HttpRequestHelper {
    private static Gson cypher = new Gson();

    public static String request(HttpMethod method, String uri) throws IOException {
        return request(method, uri, null);
    }

    public static String request(HttpMethod method, String uri, Object parameter) throws IOException {
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        connection.setRequestMethod(method.name());
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        if(parameter != null) {
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = cypher.toJson(parameter).getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
        }

        if(connection.getResponseCode() == 500)
        {
            throw new IOException("Http request returned an error 500 !");
        }
        String result = readResponse(connection.getInputStream());
        connection.disconnect();
        return result;
    }

    private static String readResponse(InputStream response) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(response));

        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    public static <T> T get(String uri, Class<T> clazz) throws IOException {
        return cypher.fromJson(HttpRequestHelper.request(GET, uri), clazz);
    }

    public static <T> ArrayList<T> getAll(String uri, Class<T> klass) throws IOException {
        Type listType = TypeToken.getParameterized(ArrayList.class, klass).getType();
        return cypher.<ArrayList>fromJson(HttpRequestHelper.request(GET, uri), listType);
    }

    public static void put(String uri, Object parameter) throws IOException {
        request(PUT, uri, parameter);
    }
}