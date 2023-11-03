package main.java.dio.desafiopadroesdeprojeto.routers;

import org.springframework.web.bind.annotation.RestController;

import main.java.dio.desafiopadroesdeprojeto.controller.ClientController;

@RestController
public class ClientRouter {

    @Autowired
    private ClientController clientController;

    @PostMapping("/signup")
    public Client createClient(@RequestBody ClientDTO clientDTO) {
        return clientController.createClient(clientDTO);
    }

    @PostMapping("/login")
    public String clientLogin(@RequestBody ClientLogin clientLogin) {
        return clientController.clientLogin(clientLogin);
    }
}
