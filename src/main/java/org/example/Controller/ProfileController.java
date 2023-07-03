package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1 -> profileService.getAllProfileList();
                case 2 -> profileService.searchProfile();
                case 3 -> profileService.changeStatus();
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
