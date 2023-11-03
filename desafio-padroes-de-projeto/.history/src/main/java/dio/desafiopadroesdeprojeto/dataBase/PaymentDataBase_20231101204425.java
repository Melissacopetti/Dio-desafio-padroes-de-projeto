package dio.desafiopadroesdeprojeto.dataBase;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dio.desafiopadroesdeprojeto.model.payment.PaymentMethod;
import dio.desafiopadroesdeprojeto.model.payment.PaymentStatus;
import dio.desafiopadroesdeprojeto.service.PaymentPreparedStatementCallback;


@Repository
public class PaymentDataBase extends BaseDataBase {

    public PaymentDataBase(DataSource dataSource) {
        super(dataSource);
    }

    private static final String TABLE = "payments";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createPayment(Payment payment) {
        jdbcTemplate.execute("INSERT INTO " + TABLE + "(payment_id,\n" +
                                                 " client_id,\n" +
                                                 " method,\n" +
                                                 " amount,\n" +
                                                 " card_holder_name,\n" +
                                                 " card_number,\n" +
                                                 " card_exp_date,\n" +
                                                 " card_cvv,\n" +
                                                 " status)\n" +
                                                 " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                    new PaymentPreparedStatementCallback(payment));
    }
    
    

    public List<Payment> getPayments() {
      return jdbcTemplate.query("SELECT * FROM " + TABLE, (resultSet, rowNum) -> {
            Payment payment = new Payment();
            payment.setPaymentId(Long.parseLong(resultSet.getString("payment_id")));
            payment.setClientId(Long.parseLong(resultSet.getString("client_id")));
            payment.setMethod(PaymentMethod.valueOf(resultSet.getString("method")));
            payment.setAmount(resultSet.getDouble("amount"));
            payment.setCardHolderName(resultSet.getString("card_holder_name"));
            payment.setCardNumber(resultSet.getString("card_number"));
            payment.setCardExpDate(resultSet.getString("card_exp_date"));
            payment.setCardCvv(resultSet.getString("card_cvv"));
            payment.setStatus(PaymentStatus.valueOf(resultSet.getString("status")));
            return payment;
        });
         
    }
}
