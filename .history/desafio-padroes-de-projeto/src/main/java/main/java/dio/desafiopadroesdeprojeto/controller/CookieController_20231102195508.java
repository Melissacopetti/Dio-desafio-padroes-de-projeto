package main.java.dio.desafiopadroesdeprojeto.controller;


import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CookieController {

    @Autowired
    private CookieStore cookieStore;

    @GetMapping("/set-cookie")
    public void setCookie() {
        Cookie servletCookie = new Cookie("name", "value");
        servletCookie.setMaxAge(60 * 60 * 24);

        CookieBuilder cookieBuilder = new CookieBuilder();
        cookieBuilder.setName(servletCookie.getName());
        cookieBuilder.setValue(servletCookie.getValue());
        cookieBuilder.setDomain(servletCookie.getDomain());
        cookieBuilder.setPath(servletCookie.getPath());
        cookieBuilder.setMaxAge(servletCookie.getMaxAge());
        cookieBuilder.setSecure(servletCookie.getSecure());

        Cookie clientCookie = cookieBuilder.build();

        cookieStore.addCookie(clientCookie);
    }

    @GetMapping("/get-cookie")
    public String getCookie() {
        List<Cookie> cookies = cookieStore.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("name")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
