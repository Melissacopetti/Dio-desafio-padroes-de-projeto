package test.java.dio.desafiopadroesdeprojeto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ch.qos.logback.core.net.server.Client;
import main.java.dio.desafiopadroesdeprojeto.model.Clients.ClientDTO;

@SpringBootTest
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
 public Payment createPayment(PaymentDTO paymentDTO, String token) throws InvalidRequest {
    try {
        if (token == null || token.isBlank()) {
            throw new InvalidRequest("Token is required", null);
        }

        AuthenticationData authData = tokenGenerator.getToken(token);
        if (authData.getId() == null) {
            throw new InvalidRequest("Invalid token", null);
        }

        // Converte o objeto PaymentDTO em um objeto Payment
        Payment newPayment = new Payment(paymentDTO.getId(),
                paymentDTO.getMethod(),
                paymentDTO.getAmount(),
                paymentDTO.getCardHolderName(),
                paymentDTO.getCardNumber(),
                paymentDTO.getCardExpDate(),
                paymentDTO.getCardCvv(),
                PaymentStatus.ANALYSIS);

        // Cria o pagamento no banco de dados
        paymentDatabase.createPayment(newPayment);

        // Retorna o pagamento criado
        return newPayment;
    } catch (InvalidRequest e) {
        throw e;
    } catch (Exception e) {
        throw new InvalidRequest("Internal error", e);
    }
}
