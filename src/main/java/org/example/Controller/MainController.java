package org.example.Controller;

import org.example.Util.GetAction;
import org.example.container.ComponentContainer;
import org.example.dto.ProfileDto;
import org.example.service.ProfileService;

public class MainController {
    private ProfileService profileService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

//                case 1 -> bookList();
//                case 2 -> Search();
                case 3 -> login();
                case 4 -> registration();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. BookList.
                2. Search. Enter query: (search by title or author or category)
                3. Login.
                4. Registration.
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

        ProfileDto profile = new ProfileDto(name, surname,  login, password, phone);

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
