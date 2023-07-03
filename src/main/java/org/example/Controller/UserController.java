package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.service.BookService;
import org.example.service.TakenBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
@Setter
public class UserController {
    @Autowired
    private BookService bookService;
    @Autowired
    private TakenBookService takenBookService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()) {

                case 1 -> bookService.bookList();
                case 2 -> bookService.search();
                case 3 -> takenBookService.takeBook();
                case 4 -> takenBookService.returnBook();
                case 5 -> takenBookService.booksOnHand();
                case 6 -> takenBookService.takeBookHistory();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**Student Menu**");
        System.out.println("""
                1. BookList.
                2. Search. Enter query: (search by title or author or category)
                3. takeBook.
                4. returnBook.
                5. booksOnHand.
                6. takeBookHistory.
                0. Exit.""");

    }
}
