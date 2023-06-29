package org.example.Controller;

import org.example.Util.GetAction;
import org.springframework.stereotype.Component;

@Component
public class StudentController {
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()) {
//                case 1 -> bookList();
//                case 2 -> Search();
//                case 3 -> takeBook();
//                case 4 -> returnBook();
//                case 5 -> booksOnHand();
//                case 6 -> takeBookHistory();
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
