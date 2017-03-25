package com.jobcenter.campus.web.controller;

import com.jobcenter.campus.model.Menu;
import com.jobcenter.campus.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by xiayun on 25/3/17.
 */
@Controller
@RequestMapping("/includes")
public class IncludesController{

    private static final String MODEL_ATTR_MENUS = "menus";

    @Autowired
    private MenuService menuService;

    @SuppressWarnings("unchecked")
    @RequestMapping("newmenu")
    public String newmenu(Model model){
        List<Menu> menus = menuService.getMenusAll();
        model.addAttribute(MODEL_ATTR_MENUS,menus);
        return "includes/newmenu";
    }

    public String util(){
        return "includes/util.jsp";
    }
}
