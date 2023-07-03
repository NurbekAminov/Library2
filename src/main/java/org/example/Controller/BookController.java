package org.example.Controller;

import lombok.Setter;
import org.example.Util.GetAction;
import org.example.service.BookService;
import org.example.service.TakenBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private TakenBookService takenBookService;
    public void start(){
        boolean t = true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1 -> bookService.bookList();
                case 2 -> bookService.search();
                case 3 -> bookService.addBook();
                case 4 -> bookService.removeBook();
                case 5 -> takenBookService.getTakenBooks();
                case 6 -> takenBookService.getBookHistory();
                case 7 -> takenBookService.getBestBookList();
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
