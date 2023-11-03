package main.java.dio.desafiopadroesdeprojeto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import dio.desafiopadroesdeprojeto.model.domain.Clients;
import dio.desafiopadroesdeprojeto.service.business.ClientBusiness;


@RestController
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    @PostMapping("/create-client")
    public ResponseEntity<String> createClient(@RequestBody ClientDTO newClient) {
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
