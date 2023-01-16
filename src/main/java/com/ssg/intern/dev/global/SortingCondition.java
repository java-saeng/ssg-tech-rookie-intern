package com.ssg.intern.dev.global;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortingCondition {

    NEWER("최근 저장 순"),
    OLDER("오래된 순"),
    RECOMMENDATION("추천해요 순");

    private final String condition;

    public String getCondition() {
        return condition;
    }
}
