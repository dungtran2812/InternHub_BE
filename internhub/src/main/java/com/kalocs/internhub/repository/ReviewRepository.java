package com.kalocs.internhub.repository;

import com.kalocs.internhub.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Review findReviewById(UUID id);
}
