package com.work.restorauntautoservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @Transient
    private String matchingPassword;
}
