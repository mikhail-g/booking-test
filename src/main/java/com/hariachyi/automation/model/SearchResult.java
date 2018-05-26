package com.hariachyi.automation.model;

import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data(staticConstructor = "of")
public class SearchResult {

    @NonNull
    private BigDecimal totalPrice;
    @NonNull
    private BigDecimal reviewScore;
}
