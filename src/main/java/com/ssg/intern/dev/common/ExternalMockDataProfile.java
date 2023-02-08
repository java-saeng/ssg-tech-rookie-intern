package com.ssg.intern.dev.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ExternalMockDataProfile {

    private ProductProfile productProfile;
    private SpecialReviewProfile specialReviewProfile;
    private HashTagProfile hashTagProfile;

    @Builder
    public ExternalMockDataProfile(final ProductProfile productProfile, final SpecialReviewProfile specialReviewProfile,
                                   final HashTagProfile hashTagProfile) {
        this.productProfile = productProfile;
        this.specialReviewProfile = specialReviewProfile;
        this.hashTagProfile = hashTagProfile;
    }
}
