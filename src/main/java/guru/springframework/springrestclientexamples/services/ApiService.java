package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import reactor.core.publisher.Flux;

public interface ApiService {

    Flux<User> getUsers(Integer limit);
}
