package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ApiServiceImpl implements ApiService {

    private final String apiUrl;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Flux<User> getUsers(Integer limit) {
        return WebClient
                .create(apiUrl)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("_limit", limit).build())
                .retrieve()
                .bodyToFlux(User.class);
    }
}
