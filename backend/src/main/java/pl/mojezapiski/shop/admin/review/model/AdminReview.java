package pl.mojezapiski.shop.admin.review.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Getter
public class AdminReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String authorName;
    private String content;
    private boolean moderated;
}
