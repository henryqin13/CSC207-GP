package data_access;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class OpenAI extends APIClient {

    public OpenAI(String endpoint, String key) {
        super(endpoint, key);
    }

    public String getResponse(String prompt) {
        String requestBody = String.format("{"
                + "\"model\": \"gpt-4\","
                + "\"messages\": ["
                + "    {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},"
                + "    {\"role\": \"user\", \"content\": \"%s\"}"
                + "]"
//                + "\"max_tokens\": 50"
                + "}", prompt);

        HttpResponse<String> response = super.getData(requestBody);
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            if (response.body() != null && !response.body().isBlank()) {
                return getContent(response.body());
            } else {
                System.out.println("Received an empty response.");
            }
        } else {
            System.err.println("Failed to get a valid response. Status code: " + response.statusCode());
            if (response.body() != null) {
                System.err.println("Error details: " + response.body());
            }
        }
        return null;
    }

    public String getContent(String body) {
        Pattern pattern = Pattern.compile("\"content\":\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(body);

        String content = null;

        if (matcher.find()) {
            content = matcher.group(1);
        }

        return content;
    }



}

