package com.example.asweprj.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.asweprj.demo.models.MenuItem;
import com.example.asweprj.demo.repositories.MenuItemRepository;

@Service
public class MenuService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAllByOrderBySortOrder();
    }
}
