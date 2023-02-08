package com.ssg.intern.dev.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductProfile {

    private String name;
    private String imageUrl;
    private int price;
    private float starScore;
    private int discountPercent;

    @Builder
    public ProductProfile(final String name, final String imageUrl, final int price, final float starScore,
                          final int discountPercent) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.starScore = starScore;
        this.discountPercent = discountPercent;
    }
}
