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
@Document("purchase")
public class Purchase {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id = null;
    private String waitressName;
    private double price;
    private List<DishPair> dishList;

}
