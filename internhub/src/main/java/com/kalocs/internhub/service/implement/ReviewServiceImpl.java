package com.kalocs.internhub.service.implement;

import com.kalocs.internhub.business.ReviewBusiness;
import com.kalocs.internhub.entity.Review;
import com.kalocs.internhub.model.ReviewDTO;
import com.kalocs.internhub.service.ReviewService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewBusiness reviewBusiness;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReviewDTO> getReview() {
        log.info("getReview() ReviewServiceImpl start");
        List<ReviewDTO> reviews = reviewBusiness.getReview().stream().map(review -> modelMapper.map(review, ReviewDTO.class)).toList();
         log.info("getReview() ReviewServiceImpl end | {}", reviews);
        return reviews;
    }
}
