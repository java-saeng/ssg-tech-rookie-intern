package com.ssg.intern.dev.domain.feed.presentation.model;

import com.ssg.intern.dev.global.SortingCondition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedProfileConditionRequest {

    private SortingCondition condition;

    public FeedProfileConditionRequest(final SortingCondition condition) {
        this.condition = condition;
    }
}
