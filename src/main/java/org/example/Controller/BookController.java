package org.example.Controller;

import org.example.Util.GetAction;
import org.springframework.stereotype.Component;

@Component
public class BookController {
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

//                case 1 -> bookList();
//                case 2 -> search();
//                case 3 -> addBook();
//                case 4 -> removeBook();
//                case 5 -> booksOnHand();
//                case 6 -> bookHistory();
//                case 7 -> bestBooks();
                case 0 -> t = false;
            }
        }
    }
    public void show() {
        System.out.println("**MENU**");
        System.out.println("""
                1. BookList.
                2. Search. Enter query: (search by title or author or category)
                3. addBook.
                4. removeBook.
                5. booksOnHand.
                6. bookHistory.
                7. bestBooks.
                0. Exit.""");
    }
}
