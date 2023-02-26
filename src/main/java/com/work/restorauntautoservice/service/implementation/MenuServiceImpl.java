package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.exception.NoSuchMenuException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Menu;
import com.work.restorauntautoservice.repository.MenuRepository;
import com.work.restorauntautoservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu editMenu(MenuType menuType, List<Dish> dishList) {
        Menu menuToUpdate = menuRepository.findByMenuType(menuType);
        menuToUpdate.setDishList(dishList);
        return menuRepository.save(menuToUpdate);
    }

    @Override
    public void deleteMenu(MenuType menuType) {
        menuRepository.delete(findMenu(menuType));
    }

    @Override
    public Menu findMenu(MenuType menuType) {
        if (menuRepository.findByMenuType(menuType) != null) {
            return menuRepository.findByMenuType(menuType);
        } else {
            throw new NoSuchMenuException("No such menu exception");
        }
    }

    @Override
    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }
}
