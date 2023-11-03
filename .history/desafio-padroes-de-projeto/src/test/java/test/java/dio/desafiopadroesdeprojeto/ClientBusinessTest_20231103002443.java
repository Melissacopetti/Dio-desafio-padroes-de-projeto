package test.java.dio.desafiopadroesdeprojeto;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.reactive.function.client.ClientRequest;

public class ClientBusinessTest {

    @Mock
    private ClientRequest clientRepository;

    @Test
    public void shouldCreateClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setEmail("johndoe@example.com");
        clientDTO.setPassword("password");

        // When
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(new Client());

        ClientBusiness clientBusiness = new ClientBusiness(clientRepository);
        Client client = clientBusiness.createClient(clientDTO);

        // Then
        assertEquals(clientDTO.getName(), client.getName());
        assertEquals(clientDTO.getEmail(), client.getEmail());
        assertEquals(clientDTO.getPassword(), client.getPassword());
    }
}
