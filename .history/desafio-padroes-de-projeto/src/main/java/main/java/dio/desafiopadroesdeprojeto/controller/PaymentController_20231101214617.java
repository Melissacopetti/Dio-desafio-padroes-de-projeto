package main.java.dio.desafiopadroesdeprojeto.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import main.java.dio.desafiopadroesdeprojeto.service.business.PaymentBusiness;
import main.java.dio.desafiopadroesdeprojeto.customErrors.InvalidRequest;
import main.java.dio.desafiopadroesdeprojeto.model.domain.Payment;
import dio.desafiopadroesdeprojeto.model.payment.PaymentDTO;

import java.io.InvalidClassException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentBusiness paymentBusiness;

    public PaymentController(PaymentBusiness paymentBusiness) {
        this.paymentBusiness = paymentBusiness;
    }

    @PostMapping("/create")
    public Payment createPayment(@RequestBody @Validated PaymentDTO newPayment) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");

            return paymentBusiness.createPayment(newPayment, token);
        } catch (InvalidClassException e) {
            throw new InvalidRequest("Pagamento inválido: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao criar o pagamento: " + e.getMessage());
        }
    }

    @GetMapping("/get")
    public List<Payment> getPayments() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = request.getHeader("Authorization");

            return paymentBusiness.getPayments(token);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao buscar os pagamentos: " + e.getMessage());
        }
    }

    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<String> handleInvalidPaymentException(InvalidPaymentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    public Payment createPayment(dio.desafiopadroesdeprojeto.routers.PaymentDTO paymentDTO) {
        return null;
    }
}

