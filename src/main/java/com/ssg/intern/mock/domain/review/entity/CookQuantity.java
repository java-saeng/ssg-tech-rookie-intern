package com.ssg.intern.mock.domain.review.entity;

import lombok.Getter;

@Getter
public enum CookQuantity {

    SOLO("1인분"),
    COUPLE("2~3인분"),
    FAMILY("3~4인분"),
    PARTY("5~10인분"),
    FESTIVAL("5~10인분");

    private final String quantity;

    CookQuantity(final String quantity) {
        this.quantity = quantity;
    }
}
