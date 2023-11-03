package main.java.dio.desafiopadroesdeprojeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.dio.desafiopadroesdeprojeto.service.business.ClientBusiness;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientLogin;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;

@RestController
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    @PostMapping("/create-client")
    public ResponseEntity<Clients> createClient(@RequestBody ClientDTO newClient) {
        try {
            Clients client = clientBusiness.createClient(newClient);
            setCookie("Authorization", token);
            return ResponseEntity.ok().body(client);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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

