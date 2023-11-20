package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class APIClient {

    private final HttpClient httpClient;
    private final String baseUrl;

    private final String key;

    public APIClient(String baseUrl, String key) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.key = key;
    }

    public String getData(String endpoint) {
        String url = baseUrl + endpoint;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
