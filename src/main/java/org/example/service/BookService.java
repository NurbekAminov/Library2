package org.example.service;

import lombok.Setter;
import org.example.dto.BookDto;
import org.example.dto.CategoryDto;
import org.example.entity.BookEntity;
import org.example.entity.CategoryEntity;
import org.example.repository.BookRepository;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Service
@Setter
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Scanner scanner;

    @Autowired
    private Random random;
    public void bookList() {
        List<BookDto> bookList = bookRepository.getBookList();
        if (bookList.size() == 0){
            System.out.println("List empty");
            return;
        }
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategory()+", visible = "+n.getVisible());
        });
    }

    public void search() {
        System.out.print("ENTER TITLE or AUTHOR or CATEGORY -> ");
        String value = scanner.nextLine();
        List<BookDto> bookList = bookRepository.searchBook(value);
        if (bookList.size() == 0){
            System.err.println("List empty");
            return;
        }
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategory());
        });
    }

    public void getBookListByCategory() {
        List<CategoryDto> categoryList = categoryRepository.getCategoryList();
        categoryList.forEach((n)->{
            System.out.println(n.getId()+". "+n.getName());
        });
        int id;
        while (true){
            System.out.print("Choose id  ");
            id = scanner.nextInt();
            boolean bool = false;
            for (CategoryDto c : categoryList) {
                if (id == c.getId()){
                    bool = true;
                    break;
                }
            }
            if (bool){
                break;
            }
        }
        List<BookDto> bookList = bookRepository.getBookListByCategory(id);
        if (bookList.size() == 0){
            System.err.println("Nothing found");
            return;
        }
        bookList.forEach((n)->{
            System.out.println("id = "+n.getId()+", title = "+n.getTitle()+", author = "+n.getAuthor()+", category = "+n.getCategory());
        });

    }

    public void addBook() {
        System.out.print("ENTER BOOK TITLE -> ");
        String title = scanner.nextLine();

        System.out.print("ENTER BOOK AUTHOR -> ");
        String author = scanner.nextLine();

        System.out.print("ENTER BOOK CATEGORY -> ");
        String categoryName = scanner.nextLine();
        CategoryEntity categoryEntity = categoryRepository.getCategoryByName(categoryName);
        if (categoryEntity == null){
            System.out.println("Category not found");
            return;
        }

        System.out.print("ENTER AVAILABLE DAY -> ");
        int availableDay = scanner.nextInt();

        int rand = random.nextInt(1,24);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(title);
        bookEntity.setAuthor(author);
        bookEntity.setCategory(categoryEntity);
        bookEntity.setVisible(true);
        bookEntity.setAvailableDate(availableDay);
        bookEntity.setCreatedDate(LocalDateTime.now());
        bookEntity.setPublishedDate(bookEntity.getCreatedDate().minusMonths(rand));
        boolean result = bookRepository.addBook(bookEntity);
        if (result){
            System.out.println("Book added");
        }else {
            System.out.println("Book not added");
        }
    }
    public void removeBook() {
        System.out.print("ENTER BOOK ID -> ");
        int id = scanner.nextInt();
        int result = bookRepository.removeBook(id);
        if (result > 0){
            System.out.println("Book removed");
        }else {
            System.out.println("Book not removed");
        }
    }
}
