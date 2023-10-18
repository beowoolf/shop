package pl.mojezapiski.shop.common.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojezapiski.shop.common.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySlug(String slug);

    Page<Product> findByCategoryId(Long id, Pageable pageable);

    List<Product> findTop10BySalePriceIsNotNull();

}
