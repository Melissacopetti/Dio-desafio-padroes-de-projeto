package main.java.dio.desafiopadroesdeprojeto.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import main.java.dio.desafiopadroesdeprojeto.customErrors.CustomError;
import main.java.dio.desafiopadroesdeprojeto.customErrors.InvalidRequest;
import main.java.dio.desafiopadroesdeprojeto.dataBase.ClientDataBase;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientLogin;
import main.java.dio.desafiopadroesdeprojeto.service.HashManager;
import main.java.dio.desafiopadroesdeprojeto.service.IdGenerator;
import main.java.dio.desafiopadroesdeprojeto.service.TokenGenerator;

@Service
public class ClientBusiness {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    private ClientDataBase clientDatabase;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private HashManager hashManager;

    public ClientBusiness(ClientDataBase clientDatabase) {
        this.clientDatabase = clientDatabase;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidCpf(String cpf) {
        Pattern pattern = Pattern.compile("^[0-9]{11}$");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    @Transactional
    public String createClient(ClientDTO client) throws CustomError {
        clientDatabase.createClient(client);
        try {
            final String name = client.getName();
            final String email = client.getEmail();
            final String password = client.getPassword();
            final String cpf = client.getCpf();

            if (name == null || email == null || password == null || cpf == null ||
                    name.isEmpty() || email.isEmpty() || password.isEmpty() || cpf.isEmpty()) {
                throw new InvalidRequest("Todos os campos devem ser preenchidos", null);
            }

            if (!emailValidator.isValidEmail(email)) {
                throw new InvalidRequest("Email inválido", null);
            }

            if (!cpfValidator.isValidCpf(cpf)) {
                throw new InvalidRequest("CPF inválido", null);
            }

            if (password.length() < 8) {
                throw new InvalidRequest("Sua senha deve ter 8 dígitos no mínimo", null);
            }

            String id = idGenerator.generateId();
            String hashPassword = hashManager.hash(password);

            Clients newClient = new Clients();
            newClient.setName(name);
            newClient.setEmail(email);
            newClient.setPassword(hashPassword);
            newClient.setCpf(cpf);

            clientDatabase.createClient(newClient);

            String token = tokenGenerator.generateToken(id);
            return token;
        } catch (Exception e) {
            throw new CustomError(400, e.getMessage());
        }
    }

    @Transactional
    public String clientLogin(ClientLogin login) throws CustomError {
        try {
            final String email = login.getEmail();
            final String password = login.getPassword();

            if (!emailValidator.isValidEmail(email)) {
                throw new InvalidRequest("Email inválido");
            }
            if (password.length() < 8) {
                throw new InvalidRequest();
            }

            Client client = clientDatabase.clientLogin(email);

            boolean isValidPass = hashManager.compare(password, client.getPassword());

            if (!isValidPass) {
                throw new InvalidRequest();
            }

            String token = tokenGenerator.generateToken(client.getId());

            return token;
        } catch (Exception e) {
            throw new CustomError(400, e.getMessage());
        }
    }
}
