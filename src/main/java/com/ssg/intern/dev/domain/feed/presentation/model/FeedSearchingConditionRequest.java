package com.ssg.intern.dev.domain.feed.presentation.model;

import com.ssg.intern.mock.domain.review.entity.CookLevel;
import com.ssg.intern.mock.domain.review.entity.CookQuantity;
import com.ssg.intern.mock.domain.review.entity.CookTime;
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
