package org.example.Controller;

import org.example.Util.GetAction;
import org.springframework.stereotype.Component;

@Component
public class CategoryController {
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

//                case 1 -> categoryList();
//                case 2 -> deleteCategory();
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
