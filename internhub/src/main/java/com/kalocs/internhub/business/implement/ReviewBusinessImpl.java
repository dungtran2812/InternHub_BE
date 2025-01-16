package com.kalocs.internhub.business.implement;

import com.kalocs.internhub.business.ReviewBusiness;
import com.kalocs.internhub.entity.Review;
import com.kalocs.internhub.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ReviewBusinessImpl implements ReviewBusiness {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReview() {
        log.info("getReview() ReviewBusinessImpl start");
        List<Review> reviews = reviewRepository.findAll();
        log.info("getReview() ReviewBusinessImpl end | {}", reviews);
        return reviews;
    }
}
