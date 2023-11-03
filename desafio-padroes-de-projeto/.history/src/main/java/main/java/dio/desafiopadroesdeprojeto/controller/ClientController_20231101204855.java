package main.java.dio.desafiopadroesdeprojeto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dio.desafiopadroesdeprojeto.model.domain.Clients;
import main.java.dio.desafiopadroesdeprojeto;


@RestController
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    @PostMapping("/create-client")
    public ResponseEntity<String> createClient(@RequestBody Clients newClient) {
        try {
            String token = clientBusiness.createClient(newClient);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> clientLogin(@RequestBody ClientLogin login) {
        try {
            String token = clientBusiness.clientLogin(login);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
