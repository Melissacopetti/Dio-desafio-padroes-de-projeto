package main.java.dio.desafiopadroesdeprojeto.controller;


import java.util.Map;


import org.apache.http.client.CookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import javax.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CookieController {

    @Autowired
    private CookieStore cookieStore;

    public static BasicClientCookie convert(Cookie servletCookie) {
    BasicClientCookie clientCookie = new BasicClientCookie(servletCookie.getName(), servletCookie.getValue());
    clientCookie.setDomain(servletCookie.getDomain());
    clientCookie.setPath(servletCookie.getPath());
    clientCookie.setMaxAge(servletCookie.getMaxAge());
    clientCookie.setSecure(servletCookie.getSecure());
    return clientCookie;
}

    @GetMapping("/set-cookie")
    public void setCookie() {
  
        Cookie cookie = new Cookie("name", "value");

        cookie.setMaxAge(60 * 60 * 24);

    
        cookie.setPath("/");

        cookieStore.addCookie(cookie);;
    }

    @GetMapping("/get-cookie")
    public String getCookie() {
     
        Cookie cookie = cookieStore.getCookie("name");

    
        String value = cookie.getValue();

        return value;
    }
}
