package data_access;

import use_case.DataAccessInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class APIClient implements DataAccessInterface {

    private final HttpClient httpClient;
    private final String baseUrl;

    private final String key;

    public APIClient(String baseUrl, String key) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.key = key;
    }

    @Override
    public HttpResponse<String> getData(String requestBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + key)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
