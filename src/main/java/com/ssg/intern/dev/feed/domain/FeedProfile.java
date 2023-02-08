package com.ssg.intern.dev.feed.domain;

import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.ProductProfile;
import com.ssg.intern.dev.common.SpecialReviewProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedProfile {

    private FeedReactionProfile feedReactionProfile;
    private ProductProfile productProfile;
    private SpecialReviewProfile specialReviewProfile;
    private HashTagProfile hashTagProfile;

    @Builder
    public FeedProfile(final FeedReactionProfile feedReactionProfile, final ProductProfile productProfile,
                       final SpecialReviewProfile specialReviewProfile, final HashTagProfile hashTagProfile) {
        this.feedReactionProfile = feedReactionProfile;
        this.productProfile = productProfile;
        this.specialReviewProfile = specialReviewProfile;
        this.hashTagProfile = hashTagProfile;
    }
}
