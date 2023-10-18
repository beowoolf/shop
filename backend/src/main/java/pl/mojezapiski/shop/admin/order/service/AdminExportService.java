package pl.mojezapiski.shop.admin.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojezapiski.shop.admin.order.model.AdminOrder;
import pl.mojezapiski.shop.admin.order.repositor.AdminOrderRepository;
import pl.mojezapiski.shop.common.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminExportService {

    private final AdminOrderRepository orderRepository;

    public List<AdminOrder> exportOrders(LocalDateTime from, LocalDateTime to, OrderStatus orderStatus) {
        return orderRepository.findAllByPlaceDateIsBetweenAndOrderStatus(from, to, orderStatus);
    }
}
