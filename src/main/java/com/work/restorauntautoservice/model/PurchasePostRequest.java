package com.work.restorauntautoservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasePostRequest {

    private String waitressName;
    private double price;
    private List<NestedDish> dishList;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NestedDish {
        public String name;
        public int count;
    }

}
