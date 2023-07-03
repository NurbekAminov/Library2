package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class StudentController {
    @Autowired
    private ProfileService profileService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1 -> profileService.getStudentList();
                case 2 -> profileService.searchStudent();
                case 3 -> profileService.blockingStudent();
                case 4 -> profileService.activateStudent();
                case 5 -> profileService.studentByBook();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. BookList.
                2. Search. Enter query: (search by name or surname or login or phone)
                3. blockStudent.
                4. activateStudent.
                5. studentByBook.
                0. Exit.""");
    }
}
