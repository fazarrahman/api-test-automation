package org.apitestautomation.springboot.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class Order {
    @Getter
    @Setter
    @JsonProperty("order_id")
    private String Id;

    @Getter
    @Setter
    @JsonProperty("order_description")
    private String Description;

    @Getter
    @Setter
    @JsonProperty("order_status")
    private String Status;

    @Getter
    @Setter
    @JsonProperty("last_updated_timestamp")
    private Long LastUpdatedTimestamp;

    @Getter
    @Setter
    @JsonProperty("special_order")
    private Boolean SpecialOrder;
}
