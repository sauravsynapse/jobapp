package com.jobex.jobapp.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> findAllByReviewId(Long companyId);

    boolean createReview(Review review, Long companyId);

    Review findReviewByCompanyIdAndReviewId(Long companyId, Long reviewId);

    boolean updateReviewByCompanyIdAndReviewId(Review review, Long companyId, Long reviewId);

    boolean deleteReviewByCompanyIdAndReviewId(Long companyId, Long reviewId);
}
