package org.testautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecordTwoProducts(
        @JsonProperty("productNumber") int productNumber1,
        @JsonProperty("sizeOptions") int sizeOption1,
        @JsonProperty("colorOption") int colorOption1,
        @JsonProperty("cartIndex") int cartIndex1,
        @JsonProperty("productNumber") int productNumber2,
        @JsonProperty("sizeOptions") int sizeOption2,
        @JsonProperty("colorOption") int colorOption2,
        @JsonProperty("cartIndex") int cartIndex2) {
}
