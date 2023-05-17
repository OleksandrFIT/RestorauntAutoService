package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Menu;
import com.work.restorauntautoservice.model.MenuRequest;

import java.util.List;

public interface MenuService {
    MenuRequest createMenu(MenuRequest menu);
    Menu editMenu(String menuType, List<Dish> dishList);
    void deleteMenu(String menuType);
    Menu findMenu(String menuType);
    List<MenuRequest> findAllMenus();
}
