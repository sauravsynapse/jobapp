package com.jobex.jobapp.reviews.impl;

import com.jobex.jobapp.companies.Company;
import com.jobex.jobapp.companies.CompanyService;
import com.jobex.jobapp.reviews.Review;
import com.jobex.jobapp.reviews.ReviewRepository;
import com.jobex.jobapp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> findAllByReviewId(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Review review, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if(company!=null)
        {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Review findReviewByCompanyIdAndReviewId(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        Review review = reviews.stream().filter(a -> a.getId()==reviewId).findFirst().orElse(null);
        return review;
    }

    @Override
    public boolean updateReviewByCompanyIdAndReviewId(Review updatedReview, Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean deleteReviewByCompanyIdAndReviewId(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(reviewId))
        {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompanyById(companyId,company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else
            return false;
    }
}
