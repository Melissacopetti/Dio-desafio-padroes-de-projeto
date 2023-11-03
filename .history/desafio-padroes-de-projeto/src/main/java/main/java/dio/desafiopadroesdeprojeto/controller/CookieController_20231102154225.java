package main.java.dio.desafiopadroesdeprojeto.controller;

import java.net.CookieStore;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CookieController {

    @Autowired
    private CookieStore cookieStore;

    @GetMapping("/set-cookie")
    public void setCookie() {
        // Cria um cookie
        Cookie cookie = new Cookie("name", "value");

        // Define o tempo de expiração do cookie
        cookie.setMaxAge(60 * 60 * 24);

        // Define o caminho do cookie
        cookie.setPath("/");

        // Adiciona o cookie à loja de cookies
        cookieStore.addCookie(cookie);
    }

    @GetMapping("/get-cookie")
    public String getCookie() {
        // Obtém o cookie da loja de cookies
        Cookie cookie = cookieStore.getCookie("name");

        // Obtém o valor do cookie
        String value = cookie.getValue();

        // Retorna o valor do cookie
        return value;
    }
}
