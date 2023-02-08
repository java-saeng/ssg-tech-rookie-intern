package com.ssg.intern.dev.common;

import com.ssg.intern.dev.external.speical_review.domain.CookLevel;
import com.ssg.intern.dev.external.speical_review.domain.CookQuantity;
import com.ssg.intern.dev.external.speical_review.domain.CookTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FeedSearchingConditionRequest {

    private String hashTag;
    private CookTime cookTime;
    private CookLevel cookLevel;
    private CookQuantity cookQuantity;

    @Builder
    public FeedSearchingConditionRequest(final String hashTag, final CookTime cookTime, final CookLevel cookLevel,
                                         final CookQuantity cookQuantity) {
        this.hashTag = hashTag;
        this.cookTime = cookTime;
        this.cookLevel = cookLevel;
        this.cookQuantity = cookQuantity;
    }
}
