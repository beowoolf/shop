package pl.mojezapiski.shop.order.service.mapper;

import pl.mojezapiski.shop.order.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderEmailMessageMapper {
    public static String createEmailMessage(Order order) {
        return "Twoje zamówienie o id: " + order.getId() +
                "\nData złożenia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                "\nWartość: " + order.getGrossValue() + " PLN " +
                "\n\n" +
                "\nPłatność: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nDziękujemy za zakupy.";
    }
}
