package main.java.dio.desafiopadroesdeprojeto.controller;

import org.springframework.web.bind.annotation.GetMapping;

@GetMapping("/set-cookie")
public class CookieController {
    // Cria um cookie
        Cookie cookie = new Cookie("name", "value");

        // Define o tempo de expiração do cookie
        cookie.setMaxAge(60 * 60 * 24);

        // Define o caminho do cookie
        cookie.setPath("/");

        // Adiciona o cookie à resposta
        response.addCookie(cookie);
}
