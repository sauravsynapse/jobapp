package com.jobex.jobapp.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> findAllByReviewId(@PathVariable Long companyId)
    {
        List<Review> reviewList = reviewService.findAllByReviewId(companyId);
        if(!reviewList.isEmpty())
        {
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createReviewByCompanyId(@RequestBody Review review, @PathVariable Long companyId)
    {
        if(reviewService.createReview(review, companyId))
            return new ResponseEntity("Review Created Successfully",HttpStatus.CREATED);
        return new ResponseEntity("Review not saved", HttpStatus.NOT_FOUND);
    }
}
