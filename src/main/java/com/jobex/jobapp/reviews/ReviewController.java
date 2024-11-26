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

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findReviewByReviewIdandCompanyId(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        Review review = reviewService.findReviewByCompanyIdAndReviewId(companyId,reviewId);
        if(review!=null)
        {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewByCompanyIdAndReviewId(@RequestBody Review review, @PathVariable Long companyId, @PathVariable Long reviewId)
    {
        if(reviewService.updateReviewByCompanyIdAndReviewId(review, companyId, reviewId))
            return new ResponseEntity("Review Updated Successfully",HttpStatus.CREATED);
        return new ResponseEntity("Review not saved", HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewByCompanyIdAndReviewId(@PathVariable Long companyId, @PathVariable Long reviewId)
    {
        if(reviewService.deleteReviewByCompanyIdAndReviewId(companyId, reviewId))
            return new ResponseEntity("Review Deleted Successfully",HttpStatus.OK);
        return new ResponseEntity("Review not found", HttpStatus.NOT_FOUND);
    }
    
}
