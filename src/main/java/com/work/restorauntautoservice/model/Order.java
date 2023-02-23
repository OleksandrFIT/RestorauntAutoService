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
@Document("product")
public class Order {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String code;
    private String price;

    private List<Product> productList;
    private short quantity;

}
