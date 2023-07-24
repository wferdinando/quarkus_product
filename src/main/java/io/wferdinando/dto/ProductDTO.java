package io.wferdinando.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Jacksonized
public class ProductDTO {

    private String name;

    private String description;

    private String category;

    private String model;

    private BigDecimal price;
}
