package pl.mojezapiski.shop.admin.order.model;

import lombok.Getter;
import pl.mojezapiski.shop.order.model.PaymentType;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
@Getter
public class AdminPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private PaymentType type;
    private boolean defaultPayment;
    private String note;
}
