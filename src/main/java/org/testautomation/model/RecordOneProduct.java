package org.testautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RecordOneProduct(
        @JsonProperty("productNumber") int productNumber,
        @JsonProperty("sizeOptions") int sizeOption,
        @JsonProperty("colorOption") int colorOption,
        @JsonProperty("multipleProducts") boolean multipleProduct,
        @JsonProperty("cartIndex") int cartIndex,
        @JsonProperty("email") String email,
        @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName,
        @JsonProperty("streetAddress") String address,
        @JsonProperty("city") String city,
        @JsonProperty("stateProvince") String stateProvince,
        @JsonProperty("postalCode") String postalCode,
        @JsonProperty("country") String country,
        @JsonProperty("phoneNumber") String phoneNumber,
        @JsonProperty("shippingMethod") int shippingMethod
) {
}
