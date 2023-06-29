package org.example.Controller;

import org.example.Util.GetAction;
import org.springframework.stereotype.Component;

@Component
public class StudentController {
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

//                case 1 -> studentList();
//                case 2 -> search();
//                case 3 -> blockStudent();
//                case 4 -> activateStudent();
//                case 5 -> studentByBook();
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
