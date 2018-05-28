package com.hariachyi.automation.model;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data(staticConstructor = "of")
public class SearchResultDto {

    @NonNull
    private String hotelName;
    @NonNull
    private BigDecimal totalPrice;
    @NonNull
    private BigDecimal reviewScore;

    public static SearchResultDto empty() {
        return SearchResultDto.of("", BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
