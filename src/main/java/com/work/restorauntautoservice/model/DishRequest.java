package com.work.restorauntautoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("dish")
public class DishRequest {

    @Indexed(unique = true)
    private String name;
    private short price;
    private double weight;
    private List<NestedProduct> products;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NestedProduct {
        public String name;
        public int nestedProdWeight;
    }
}
