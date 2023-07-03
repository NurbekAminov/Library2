package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.container.ComponentContainer;
import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.example.service.BookService;
import org.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class MainController {
    @Autowired
    private ProfileService profileService;
    public BookService bookService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1 -> bookService.bookList();
                case 2 -> bookService.search();
                case 3 -> bookService.getBookListByCategory();
                case 4 -> login();
                case 5 -> registration();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. BookList.
                2. Search. Enter query: (search by title or author or category)
                3. By category
                4. Login.
                5. Registration.
                0. Exit.""");
    }
    public void registration() {
        System.out.println("Enter your name: ");
        String name = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your surname:");
        String surname = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your phone:");
        String phone = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your login:");
        String login = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password:");
        String password = ComponentContainer.stringScanner.nextLine();

        ProfileEntity profile = new ProfileEntity(name, surname,  login, password, phone);

        ComponentContainer.profileService.register(profile);
    }
    public void login() {
        System.out.println("Enter your login: ");
        String login = ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password");
        String password = ComponentContainer.stringScanner.nextLine();
        profileService.login(login, password);
    }
}
