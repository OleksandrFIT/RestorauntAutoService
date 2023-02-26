package com.work.restorauntautoservice.model;

import com.work.restorauntautoservice.enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("menu")
public class Menu {
    private List<Dish> dishList;
    private MenuType menuType;
}
