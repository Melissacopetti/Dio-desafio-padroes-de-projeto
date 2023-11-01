package main.java.dio.desafiopadroesdeprojeto.routers;

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

