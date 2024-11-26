package com.jobex.jobapp.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> findAllByReviewId(Long companyId);

    boolean createReview(Review review, Long companyId);
}
