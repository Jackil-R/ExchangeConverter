package com.exchange.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country implements Serializable {

    @JsonProperty
    private String id;

    @JsonProperty
    private String currencyId;

    @JsonProperty
    private String currencyName;

    @JsonProperty
    private String currencySymbol;

    @JsonProperty
    private String name;

    @JsonProperty
    private String alpha3;

}
