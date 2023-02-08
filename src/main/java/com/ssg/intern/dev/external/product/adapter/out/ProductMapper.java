package com.ssg.intern.dev.external.product.adapter.out;

import com.ssg.intern.dev.common.ProductProfile;
import com.ssg.intern.dev.external.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductProfile mapToProductProfile(Product product) {
        return ProductProfile.builder()
                             .name(product.getName())
                             .imageUrl(product.getImageUrl())
                             .price(product.getPrice())
                             .starScore(product.getStarScore())
                             .discountPercent(product.getDiscountPercent())
                             .productInfoUrl(product.getProductInfoUrl())
                             .build();
    }
}
