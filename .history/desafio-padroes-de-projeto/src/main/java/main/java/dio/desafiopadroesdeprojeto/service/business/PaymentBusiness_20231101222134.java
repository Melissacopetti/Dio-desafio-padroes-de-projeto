packagejava dio.desafiopadroesdeprojeto.service.business;
java
import javadio.desafiopadroesdeprojeto.dataBase.*;
import javadio.desafiopadroesdeprojeto.model.Clients.AuthenticationData;
import javadio.desafiopadroesdeprojeto.customErrors.InvalidRequest;
import javadio.desafiopadroesdeprojeto.model.payment.*;
import javadio.desafiopadroesdeprojeto.model.domain.Payment;
import javadio.desafiopadroesdeprojeto.service.TokenGenerator;

import dio.desafiopadroesdeprojeto.service.IdGenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentBusiness {
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Autowired
    private PaymentDataBase paymentDatabase;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private TokenGenerator tokenGenerator;

    public PaymentBusiness(PaymentDataBase paymentDatabase) {
        this.paymentDatabase = paymentDatabase;
    }

    public Payment createPayment(Payment payment, String token) throws InvalidRequest {
        paymentDatabase.createPayment(payment);
        try {
            if (token == null || token.isBlank()) {
                throw new InvalidRequest("Token is required",null);
            }

            AuthenticationData authData = tokenGenerator.getToken(token);
            if (authData.getId() == null) {
                throw new InvalidRequest("Invalid token", null);
            }

            PaymentStatus status = PaymentStatus.ANALYSIS;
            if (payment.getMethod() == PaymentMethod.CARD) {
                status = PaymentStatus.APPROVED;
            }

            String id = idGenerator.generateId();

            Payment newPayment = new Payment(
                id,
                authData.getId(),
                payment.getMethod(),
                payment.getAmount(),
                payment.getCardHolderName(),
                payment.getCardNumber(),
                payment.getCardExpDate(),
                payment.getCardCvv(),
                status
            );

            paymentDatabase.createPayment(newPayment);
            return newPayment;
        } catch (InvalidRequest e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidRequest("Internal error", e);
        }
    }

    public List<Payment> getPayments(String token) throws InvalidRequest {
        try {
            if (token == null || token.isBlank()) {
                throw new InvalidRequest("Token is required", null);
            }

            AuthenticationData authData = tokenGenerator.getToken(token);
            if (authData.getId() == null) {
                throw new InvalidRequest("Invalid token", null);
            }

            List<Payment> payments = paymentDatabase.getPayments();
            if (payments.isEmpty()) {
                throw new InvalidRequest("No payments found", null);
            }

            return payments;
        } catch (InvalidRequest e) {
            throw e;
        } catch (Exception e) {
            throw new InvalidRequest("Internal error", e);
        }
    }
}
            
            
