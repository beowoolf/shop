package pl.mojezapiski.shop.order.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.mojezapiski.shop.common.model.OrderStatus;
import pl.mojezapiski.shop.order.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummary {
    private Long id;
    private LocalDateTime placeDate;
    private OrderStatus status;
    private BigDecimal grossValue;
    private Payment payment;
    private String redirectUrl;
}
