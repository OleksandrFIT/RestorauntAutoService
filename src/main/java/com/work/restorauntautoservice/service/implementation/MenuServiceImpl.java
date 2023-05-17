package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.enums.MenuType;
import com.work.restorauntautoservice.exception.NoSuchMenuException;
import com.work.restorauntautoservice.model.Dish;
import com.work.restorauntautoservice.model.Menu;
import com.work.restorauntautoservice.model.MenuRequest;
import com.work.restorauntautoservice.repository.MenuRepository;
import com.work.restorauntautoservice.service.DishService;
import com.work.restorauntautoservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishService dishService;

    @Override
    public MenuRequest createMenu(MenuRequest menuRequest) {
        List<Dish> dishList = menuRequest.getDishNamesList().stream()
                .map(dishService::findDish)
                .collect(Collectors.toList());
        Menu menuToCreate = new Menu(
                dishList,
                menuRequest.getMenuType()
        );

        Menu savedMenu = menuRepository.save(menuToCreate);

        List<String> returnMenuDishNamestList = new LinkedList<>();

        savedMenu.getDishList().forEach(dish ->
                    returnMenuDishNamestList.add(dish.getName())
                );

        return new MenuRequest(
                returnMenuDishNamestList,
                savedMenu.getMenuType()
        );
    }

    @Override
    public Menu editMenu(String menuType, List<Dish> dishList) {
        Menu menuToUpdate = menuRepository.findByMenuType(menuType);
        menuToUpdate.setDishList(dishList);
        return menuRepository.save(menuToUpdate);
    }

    @Override
    public void deleteMenu(String menuType) {
        menuRepository.delete(findMenu(menuType));
    }

    @Override
    public Menu findMenu(String menuType) {
        if (menuRepository.findByMenuType(menuType) != null) {
            return menuRepository.findByMenuType(menuType);
        } else {
            throw new NoSuchMenuException("No such menu exception");
        }
    }

    @Override
    public List<MenuRequest> findAllMenus() {
        List<Menu> menusList = menuRepository.findAll();
        List<MenuRequest> menusResponseList = new LinkedList<>();
        for (Menu menu : menusList) {
            List<String> dishesNames = new LinkedList<>();
            for (Dish dish : menu.getDishList()) {
                dishesNames.add(dish.getName());
            }
            menusResponseList.add(new MenuRequest(dishesNames, menu.getMenuType()));
        }
        return menusResponseList;
    }

}
