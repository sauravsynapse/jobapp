package com.jobex.jobapp.reviews.impl;

import com.jobex.jobapp.companies.Company;
import com.jobex.jobapp.companies.CompanyService;
import com.jobex.jobapp.reviews.Review;
import com.jobex.jobapp.reviews.ReviewRepository;
import com.jobex.jobapp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
