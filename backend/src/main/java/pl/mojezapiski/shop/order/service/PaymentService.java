package pl.mojezapiski.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojezapiski.shop.order.model.Payment;
import pl.mojezapiski.shop.order.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
