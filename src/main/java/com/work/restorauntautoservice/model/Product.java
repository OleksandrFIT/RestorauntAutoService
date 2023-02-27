package com.work.restorauntautoservice.model;


import com.work.restorauntautoservice.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("product")
public class Product {

    @MongoId(value = FieldType.OBJECT_ID)
    public String id;
    public String name;
    private String code;
    public ProductCategory category;
    private String provider;
    private double orderPrice;
    public double actualPrice;
    public short availableQuantity;
    public short maxQuantity;
}
