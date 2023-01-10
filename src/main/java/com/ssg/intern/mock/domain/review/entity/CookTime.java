package com.ssg.intern.mock.domain.review.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum CookTime {

    TEN("10분"),
    TWENTY("20분"),
    THIRTY("30분"),
    ONE_HOUR("1시간"),
    TWO_HOURS("2시간 이상");

    private final String time;

    CookTime(final String time) {
        this.time = time;
    }
}
