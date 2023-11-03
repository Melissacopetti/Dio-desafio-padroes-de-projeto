package main.java.dio.desafiopadroesdeprojeto.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.dio.desafiopadroesdeprojeto.controller.ClientController;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientLogin;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;

@RestController
public class ClientRouter {

    @Autowired
    private ClientController clientController;

    @PostMapping("/signup")
    public ResponseEntity<Clients> createClient(@RequestBody ClientDTO clientDTO) {
        ResponseEntity<ResponseEntity<Clients>> responseEntity = clientController.createClient(clientDTO);
        return responseEntity.getBody();
    }

    @PostMapping("/login")
    public String clientLogin(@RequestBody final ClientLogin login) {
        ResponseEntity<String> responseEntity = clientController.clientLogin(login);
        return responseEntity.getBody();
    }
}
