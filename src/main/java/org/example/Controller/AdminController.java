package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class AdminController {
    @Autowired
    BookController bookController;
    @Autowired
    CategoryController categoryController;
    @Autowired
    StudentController studentController;
    @Autowired
    ProfileController profileController;
    public void startAdmin(){
        boolean t = true;
        while (t){
            showAdmin();
            switch (GetAction.getAction()) {

                case 1 -> book();
                case 2 -> category();
                case 3 -> student();
                case 4 -> profile();
                case 0 -> t = false;
            }
        }
    }

    public void startStaff(){
        boolean t = true;
        while (t){
            showStaff();
            switch (GetAction.getAction()) {
                case 1 -> book();
                case 2 -> student();
                case 0 -> t = false;
            }
        }
    }

    public void showAdmin() {
        System.out.println("**Admin Menu**");
        System.out.println("""
                1. Book.
                2. Category
                3. Student.
                4. Profile.
                0. Exit.""");

    }

    public void showStaff() {
        System.out.println("**Admin Menu**");
        System.out.println("""
                1. Book.
                2. Student
                0. Exit.""");
    }

    public void book() {
        bookController.start();
    }
    private void category() {
        categoryController.start();
    }
    private void student() {
        studentController.start();
    }
    private void profile() {
        profileController.start();
    }
}
