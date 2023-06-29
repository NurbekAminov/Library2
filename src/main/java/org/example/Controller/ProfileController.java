package org.example.Controller;

import org.example.Util.GetAction;
import org.springframework.stereotype.Component;

@Component
public class ProfileController {
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

//                case 1 -> profileList();
//                case 2 -> searchProfile();
//                case 3 -> changeProfileStatus();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. profileList.
                2. searchProfile. Enter query: (search by name or surname or login or phone)
                3. changeProfileStatus.
                0. Exit.""");
    }
}
