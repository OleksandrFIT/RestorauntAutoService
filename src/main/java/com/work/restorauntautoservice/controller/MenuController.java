package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Menu;
import com.work.restorauntautoservice.model.MenuRequest;
import com.work.restorauntautoservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu-find/{menuType}")
    Menu findMenuById(@PathVariable String menuType) {
        return menuService.findMenu(menuType);
    }

    @GetMapping("/menu-findAll")
    List<MenuRequest> findAllMenus() {
        return menuService.findAllMenus();
    }

    @PostMapping("/menu-create")
    @ResponseStatus(HttpStatus.CREATED)
    MenuRequest createMenu(@RequestBody MenuRequest menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/menu-edit/{menuType}")
    Menu editMenu(@PathVariable String menuType, @RequestBody List<Dish> dishList) {
        return menuService.editMenu(menuType, dishList);
    }

    @DeleteMapping("/menu-delete/{menuType}")
    void deleteMenu(@PathVariable String menuType) {
        menuService.deleteMenu(menuType);
    }

}
