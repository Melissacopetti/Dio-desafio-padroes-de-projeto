package dio.desafiopadroesdeprojeto.model.payment;

import javax.persistence.Entity;
import javax.persistence.Id;

import dio.desafiopadroesdeprojeto.model.payment.PaymentMethod;
import dio.desafiopadroesdeprojeto.model.payment.PaymentStatus;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentId;
    private Long clientId;
    private PaymentMethod method;
    private double amount;
    private String cardHolderName;
    private String cardNumber;
    private String cardExpDate;
    private String cardCvv;
    private PaymentStatus status;
   

    public Payment() {
    }
    


    public Payment(long paymentId, Long clientId, PaymentMethod method, double amount, String cardHolderName, String cardNumber, String cardExpDate, String cardCvv, PaymentStatus status) {
        this.paymentId = paymentId;
        this.clientId = clientId;
        this.method = method;
        this.amount = amount;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cardExpDate = cardExpDate;
        this.cardCvv = cardCvv;
        this.status = status;
    }



    public Payment(String id, String id2, PaymentMethod method2, double amount2, String cardHolderName2,
            String cardNumber2, String cardExpDate2, String cardCvv2, PaymentStatus status2) {
    }



    public Payment(String id, String id2, PaymentMethod method2, double amount2, String cardHolderName2,
            String cardNumber2, String cardExpDate2, String cardCvv2, PaymentStatus status2) {
    }



    public long getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(long l) {
        this.paymentId = l;
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

    public PaymentStatus getStatus() {
        return this.status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

   
   
}
