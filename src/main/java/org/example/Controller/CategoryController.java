package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1 -> categoryService.getCategoryList();
                case 2 -> categoryService.addCategory();
                case 3 -> categoryService.reactivationStatusCategory();
                case 4 -> categoryService.deleteCategory();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. categoryList.
                2. deleteCategory.
                0. Exit.""");
    }
}
