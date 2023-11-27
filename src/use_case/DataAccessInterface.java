package use_case;

import java.net.http.HttpResponse;

public interface DataAccessInterface {

    HttpResponse<String> getData(String requestBody);
}
