/* package test.java.dio.desafiopadroesdeprojeto;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import main.java.dio.desafiopadroesdeprojeto.dataBase.ClientDataBase;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Clients;
import main.java.dio.desafiopadroesdeprojeto.service.business.ClientBusiness;

public class ClientBusinessTest {

    @Mock
    private ClientDataBase clientDatabase;

    @Test
    public void shouldCreateClient() {
        // Given
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setEmail("johndoe@example.com");
        clientDTO.setPassword("password");

        // When
        Clients client = new Clients();
        Mockito.when(clientDatabase.createClient(Mockito.any(Clients.class))).thenReturn(client);

        ClientBusiness clientBusiness = new ClientBusiness(clientDatabase);
        ResponseEntity<Clients> createdClient = clientBusiness.createClient(clientDTO);

        // Then
        assertEquals(clientDTO.getName(), createdClient.getName());
        assertEquals(clientDTO.getEmail(), createdClient.getEmail());
        assertEquals(clientDTO.getPassword(), createdClient.getPassword());
    }
}
 */