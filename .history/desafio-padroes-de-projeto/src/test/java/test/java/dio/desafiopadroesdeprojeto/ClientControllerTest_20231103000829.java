package test.java.dio.desafiopadroesdeprojeto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import ch.qos.logback.core.net.server.Client;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;

@SpringBootTest
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private MockMvc mockMvc;
private RestTemplate restTemplate;


    @Test
    public void shouldCreateClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setEmail("johndoe@example.com");
        clientDTO.setPassword("password");

        // When
        final ResponseEntity<Client> responseEntity = testRestTemplate.postForEntity("/clients", clientDTO, Client.class);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(clientDTO.getName(), responseEntity.getBody().getName());
        assertEquals(clientDTO.getEmail(), responseEntity.getBody().getEmail());
    }
}
