package main.java.dio.desafiopadroesdeprojeto.model.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentDTO {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long clientId;
    private PaymentMethod method;
    private double amount;
    private String cardHolderName;
    private String cardNumber;
    private String cardExpDate;
    private String cardCvv;



    public PaymentDTO() {
    }

    public PaymentDTO(Long clientId, PaymentMethod method, double amount, String cardHolderName, String cardNumber,
            String cardExpDate, String cardCvv) {
        this.clientId = clientId;
        this.method = method;
        this.amount = amount;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardExpDate = cardExpDate;
        this.cardCvv = cardCvv;
    }

     public Long getId() {
        return id;
    }
    
    public Long getClientId() {
        return this.clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public PaymentMethod getMethod() {
        return this.method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardHolderName() {
        return this.cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpDate() {
        return this.cardExpDate;
    }

    public void setCardExpDate(String cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    public String getCardCvv() {
        return this.cardCvv;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
    }
}
 