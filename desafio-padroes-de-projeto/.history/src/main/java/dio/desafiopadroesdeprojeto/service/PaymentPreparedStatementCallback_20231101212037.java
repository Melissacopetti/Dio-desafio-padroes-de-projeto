package dio.desafiopadroesdeprojeto.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCallback;

import dio.desafiopadroesdeprojeto.model.Payment;

public class PaymentPreparedStatementCallback implements PreparedStatementCallback<Void> {

    private Payment payment;

    public PaymentPreparedStatementCallback(Payment payment) {
        this.payment = payment;
    }

    @Override
    public Void doInPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setLong(1, payment.getPaymentId());
        ps.setLong(2, payment.getClientId());
        ps.setString(3, payment.getMethod().toString());
        ps.setDouble(4, payment.getAmount());
        ps.setString(5, payment.getCardHolderName());
        ps.setString(6, payment.getCardNumber());
        ps.setString(7, payment.getCardExpDate());
        ps.setString(8, payment.getCardCvv());
        ps.setString(9, payment.getStatus().toString());

        ps.execute();

        return null;
    }
}


