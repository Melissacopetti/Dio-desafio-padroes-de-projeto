package main.java.dio.desafiopadroesdeprojeto.service.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import main.java.dio.desafiopadroesdeprojeto.customErrors.CustomError;
import main.java.dio.desafiopadroesdeprojeto.customErrors.InvalidRequest;
import main.java.dio.desafiopadroesdeprojeto.dataBase.ClientDataBase;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientLogin;
import main.java.dio.desafiopadroesdeprojeto.service.CpfValidator;
import main.java.dio.desafiopadroesdeprojeto.service.EmailValidator;
import main.java.dio.desafiopadroesdeprojeto.service.HashManager;
import main.java.dio.desafiopadroesdeprojeto.service.TokenGenerator;


@Service
public class ClientBusiness {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    private ClientDataBase clientDatabase;
    

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private HashManager hashManager;

       @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private CpfValidator cpfValidator;
    @Auto
    private ClientLogin clientLogin;

    public ClientBusiness(ClientLogin clientLogin) {
        this.clientLogin = clientLogin;
    }


    public ClientBusiness(ClientDataBase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }
   
    @Transactional
    public ResponseEntity<Clients> createClient(@RequestBody ClientDTO newClient) throws CustomError {
        try {
            validateClient(newClient);
    
            Clients client = new Clients();
            client.setName(newClient.getName());
            client.setEmail(newClient.getEmail());
            client.setPassword(newClient.getPassword());
            client.setCpf(newClient.getCpf());
    
            clientDatabase.createClient(client);
            
         
            return ResponseEntity.ok().body(client);
        } catch (InvalidRequest e) {
            throw new CustomError(400, e.getMessage());
        } catch (Exception e) {
            throw new CustomError(500, e.getMessage());
        }
    }
    
    private void validateClient(ClientDTO clientDTO) throws InvalidRequest {
        if (clientDTO.getName() == null || clientDTO.getName().isEmpty()) {
            throw new InvalidRequest("O campo `name` deve ser preenchido.", null);
        }
    
        if (clientDTO.getEmail() == null || clientDTO.getEmail().isEmpty()) {
            throw new InvalidRequest("O campo `email` deve ser preenchido.", null);
        }
    
        if (!emailValidator.isValidEmail(clientDTO.getEmail())) {
            throw new InvalidRequest("O email fornecido é inválido.", null);
        }
    
        if (clientDTO.getPassword() == null || clientDTO.getPassword().isEmpty()) {
            throw new InvalidRequest("O campo `password` deve ser preenchido.", null);
        }
    
        if (clientDTO.getPassword().length() < 8) {
            throw new InvalidRequest("A senha deve ter no mínimo 8 caracteres.", null);
        }
    
        if (clientDTO.getCpf() == null || clientDTO.getCpf().isEmpty()) {
            throw new InvalidRequest("O campo `cpf` deve ser preenchido.", null);
        }
    
        if (!cpfValidator.isValidCpf(clientDTO.getCpf())) {
            throw new InvalidRequest("O CPF fornecido é inválido.", null);
        }
    }
    @Transactional
    public String clientLogin(ClientLogin login) throws CustomError {
        try {
            final String email = login.getEmail();
            final String password = login.getPassword();

            if (!emailValidator.isValidEmail(email)) {
                throw new InvalidRequest("Email inválido", null);
            }
            if (password.length() < 8) {
                throw new InvalidRequest("Sua senha deve ter ao menos 8 dígitos", null);
            }

            Clients client = clientDatabase.clientLogin(email);

            boolean isValidPass = hashManager.compare(password, client.getPassword());

            if (!isValidPass) {
                throw new InvalidRequest("Senha incorreta", null);
            }

           String token = tokenGenerator.generateToken(String.valueOf(client.getId()));


            return token;
        } catch (Exception e) {
            throw new CustomError(400, e.getMessage());
        }
    }
}
