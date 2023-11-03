package main.java.dio.desafiopadroesdeprojeto.controller;

import java.net.CookieStore;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @Autowired
    private CookieStore cookieStore;

    @GetMapping("/set-cookie")
    public void setCookie() {
  
        Cookie cookie = new Cookie("name", "value");

        cookie.setMaxAge(60 * 60 * 24);

    
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
