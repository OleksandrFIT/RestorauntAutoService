package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Menu;

import java.util.List;

public interface MenuService {
    Menu createMenu(Menu menu);
    Menu editMenu(MenuType menuType, List<Dish> dishList);
    void deleteMenu(MenuType menuType);
    Menu findMenu(MenuType menuType);
    List<Menu> findAllMenus();
}
