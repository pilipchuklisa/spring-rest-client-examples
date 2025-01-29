package guru.springframework.springrestclientexamples.controllers;

import guru.springframework.springrestclientexamples.services.ApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Controller
public class UserController {

    private final ApiService apiService;

    public UserController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/users")
    public Mono<String> post(Model model, ServerWebExchange serverWebExchange) {

        return serverWebExchange.getFormData()
                .map(m -> m.get("limit").get(0))
                .flatMap(s -> Mono.just(Integer.parseInt(s)))
                .map(limit -> model.addAttribute("users", apiService.getUsers(limit)))
                .map(m -> "userlist");
    }
}
