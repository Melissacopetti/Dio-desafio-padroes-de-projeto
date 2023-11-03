package test.java.dio.desafiopadroesdeprojeto;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import main.java.dio.desafiopadroesdeprojeto.controller.ClientController;


@SpringBootTest
public class ClientControllerTest {

    private MockMvc mockMvc;
    private RestTemplate restTemplate;

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(clientService)).build();
        restTemplate = new RestTemplate();
    }

    @Test
    public void shouldCreateClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setEmail("johndoe@example.com");
        clientDTO.setPassword("password");

        // When
        final ResponseEntity<Client> responseEntity = restTemplate.postForEntity("/clients", clientDTO, Client.class);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(clientDTO.getName(), responseEntity.getBody().getName());
        assertEquals(clientDTO.getEmail(), responseEntity.getBody().getEmail());
    }
}
