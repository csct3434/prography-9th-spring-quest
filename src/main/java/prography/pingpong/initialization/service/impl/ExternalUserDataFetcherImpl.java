package prography.pingpong.initialization.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.net.URI;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import prography.pingpong.initialization.dto.InitializeCommand;
import prography.pingpong.initialization.dto.UserData;

@Component
public class ExternalUserDataFetcherImpl implements ExternalUserDataFetcher {

    private final static String URL = "https://fakerapi.it/api/v1/users?_seed=%s&_quantity=%s&_locale=ko_KR";

    @Override
    public List<UserData> fetch(InitializeCommand command) {
        RestTemplate restTemplate = new RestTemplate();
        URI requestURI = URI.create(String.format(URL, command.getSeed(), command.getQuantity()));

        ResponseEntity<FakerApiResponse> response = restTemplate.exchange(
            requestURI, HttpMethod.GET, null, FakerApiResponse.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Fetching User Data Failed / Status Code " + response.getStatusCode().value());
        }

        if (response.getBody() == null || response.getBody().getData() == null) {
            throw new RuntimeException("Fetching User Data Failed / Empty Body(Data)");
        }
        return response.getBody().getData();
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class FakerApiResponse {

        private List<UserData> data;
    }
}
