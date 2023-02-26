package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.model.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<Menu, String> {
    Menu findByMenuType(MenuType menuType);
}
