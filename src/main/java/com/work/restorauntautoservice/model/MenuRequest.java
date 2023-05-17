package com.work.restorauntautoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("menu")
public class MenuRequest {
    private List<String> dishNamesList;
    private String menuType;

}
