package main.java.dio.desafiopadroesdeprojeto.controller;


import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.http.client.reactive.Cook
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

        cookieStore.addCookie(cookie);
    }

    @GetMapping("/get-cookie")
    public String getCookie() {
     
        Cookie cookie = cookieStore.getCookie("name");

    
        String value = cookie.getValue();

        return value;
    }
}
