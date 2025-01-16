package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.ReviewBusiness;
import com.kalocs.internhub.entity.Review;
import com.kalocs.internhub.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewBusiness reviewBusiness;

    @Override
    public List<Review> getReview() {
        log.info("getReview() ReviewServiceImpl start");
        List<Review> reviews = reviewBusiness.getReview();
         log.info("getReview() ReviewServiceImpl end | {}", reviews);
        return reviews;
    }
}
