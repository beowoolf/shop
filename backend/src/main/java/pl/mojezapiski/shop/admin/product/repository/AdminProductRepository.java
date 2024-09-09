package pl.mojezapiski.shop.admin.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojezapiski.shop.admin.product.model.AdminProduct;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
