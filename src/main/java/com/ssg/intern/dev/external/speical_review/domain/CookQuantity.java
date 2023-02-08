package com.ssg.intern.dev.external.speical_review.domain;

import lombok.Getter;

@Getter
public enum CookQuantity {

    SOLO("1인"),
    COUPLE("2~3인"),
    FAMILY("3~4인"),
    PARTY("5~10인"),
    FESTIVAL("10인 이상");

    private final String quantity;

    CookQuantity(final String quantity) {
        this.quantity = quantity;
    }
}
