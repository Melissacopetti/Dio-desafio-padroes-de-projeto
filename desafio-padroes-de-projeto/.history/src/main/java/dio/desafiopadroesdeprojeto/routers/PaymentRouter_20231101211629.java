package dio.desafiopadroesdeprojeto.routers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.desafiopadroesdeprojeto.controller.PaymentController;
import dio.desafiopadroesdeprojeto.model.dom.Payment;

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

