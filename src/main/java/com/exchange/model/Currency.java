package com.exchange.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency {

    @JsonProperty
    private String currencyName;

    @JsonProperty
    private String currencySymbol;

    @JsonProperty
    private String id;
}
