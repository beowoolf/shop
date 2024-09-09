package pl.mojezapiski.shop.admin.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.mojezapiski.shop.admin.review.model.AdminReview;

public interface AdminReviewRepository extends JpaRepository<AdminReview, Long> {
    @Query("update AdminReview r set r.moderated=true where r.id=:id")
    @Modifying
    void moderate(Long id);
}
