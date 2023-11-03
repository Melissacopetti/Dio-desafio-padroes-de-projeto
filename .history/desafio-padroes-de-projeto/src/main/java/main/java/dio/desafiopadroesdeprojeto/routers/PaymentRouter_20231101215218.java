package main.java.dio.desafiopadroesdeprojeto.routers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dio.desafiopadroesdeprojeto.controller.PaymentController;
import dio.desafiopadroesdeprojeto.model.domain.Payment;

@RestController
public class PaymentRouter {

    @Autowired
    private PaymentController paymentController;

    @PostMapping("/create-payment")
    public Payment createPayment(@RequestBody PaymentDTO paymentDTO) {
        return paymentController.createPayment(paymentDTO);
    }

    @GetMapping("/get-payments")
    public List<Payment> getPayments() {
        return paymentController.getPayments();
    }
}
