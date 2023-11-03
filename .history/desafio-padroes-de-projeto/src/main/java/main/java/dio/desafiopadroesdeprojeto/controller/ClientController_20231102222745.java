package main.java.dio.desafiopadroesdeprojeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.support.ServletConte

import main.java.dio.desafiopadroesdeprojeto.service.business.ClientBusiness;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientLogin;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;

@RestController
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    private ClientLogin clientLogin;
   

   @PostMapping("/create-client")
public ResponseEntity<ResponseEntity<Clients>> createClient(@RequestBody ClientDTO newClient) {
    try {
        String token = clientBusiness.login(clientLogin);
        ResponseEntity<Clients> client = clientBusiness.createClient(newClient);

        // Set the cookie
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30); // 30 days

        // Get the response from the Spring Boot context
        HttpServletResponse response = ServletContextUtils.getWebApplicationContext(this.getClass().getClassLoader()).getServletContext().getRequestDispatcher("/").getResponse();
        response.addCookie(cookie);

        return ResponseEntity.ok().body(client);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
    }
}


    @PostMapping("/login")
    public ResponseEntity<String> clientLogin(@RequestBody final ClientLogin login) {
        try {
            String token = clientBusiness.clientLogin(login);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

