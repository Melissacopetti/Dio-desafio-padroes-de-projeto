package main.java.dio.desafiopadroesdeprojeto.controller;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.web.bind.annotation.GetMapping;

@GetMapping("/set-cookie")
public class CookieController {
    // Cria um cookie
        Cookie cookie = new Cookie("name", "value");

        
        cookie.setMaxAge(60 * 60 * 24);

        cookie.setPath("/");

   
        response.addCookie(cookie);

        public String getCookie() {      Map<String, Cookie> cookies = request.getCookies();
        
       
            String value = cookies.get("name").getValue();
       
            return value;
        }
}
