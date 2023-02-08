package com.ssg.intern.dev.external.adapter.out;

import com.ssg.intern.dev.common.HashTagProfile;
import com.ssg.intern.dev.common.ProductProfile;
import com.ssg.intern.dev.common.SpecialReviewProfile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MockDataMapper {

    private HashTagProfile hashTagProfile;
    private ProductProfile productProfile;
    private SpecialReviewProfile specialReviewProfile;

    @Builder
    public MockDataMapper(final HashTagProfile hashTagProfile, final ProductProfile productProfile,
                          final SpecialReviewProfile specialReviewProfile) {
        this.hashTagProfile = hashTagProfile;
        this.productProfile = productProfile;
        this.specialReviewProfile = specialReviewProfile;
    }
}
