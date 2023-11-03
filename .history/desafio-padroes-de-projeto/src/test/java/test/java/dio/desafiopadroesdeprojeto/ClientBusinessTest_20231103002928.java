package test.java.dio.desafiopadroesdeprojeto;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import main.java.dio.desafiopadroesdeprojeto.dataBase.ClientDataBase;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;

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
        Client client = new Client();
        Mockito.when(clientDatabase.createClient(Mockito.any(Client.class))).thenReturn(client);

        ClientBusiness clientBusiness = new ClientBusiness(clientDatabase);
        Client createdClient = clientBusiness.createClient(clientDTO);

        // Then
        assertEquals(clientDTO.getName(), createdClient.getName());
        assertEquals(clientDTO.getEmail(), createdClient.getEmail());
        assertEquals(clientDTO.getPassword(), createdClient.getPassword());
    }
}
