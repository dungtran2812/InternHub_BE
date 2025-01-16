package com.kalocs.internhub.controller;

import com.kalocs.internhub.common.URLConstant;
import com.kalocs.internhub.entity.Review;
import com.kalocs.internhub.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(URLConstant.REVIEW)
@Log4j2
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getReview() {
        log.info("getReview() ReviewController start");
        List<Review> result = reviewService.getReview();
        log.info("getReview() ReviewController end | {}", result);
        return ResponseEntity.ok().body(result);
    }

}
