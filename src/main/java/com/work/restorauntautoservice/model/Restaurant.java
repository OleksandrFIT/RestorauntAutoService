package com.work.restorauntautoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Restaurant {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String sittingSpots;
    private String menu;

}
