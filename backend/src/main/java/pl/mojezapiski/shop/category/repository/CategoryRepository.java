package pl.mojezapiski.shop.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mojezapiski.shop.common.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findBySlug(String slug);
}
