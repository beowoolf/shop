package pl.mojezapiski.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mojezapiski.shop.order.model.Shipment;
import pl.mojezapiski.shop.order.repository.ShipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    public List<Shipment> getShipments() {
        return shipmentRepository.findAll();
    }
}
