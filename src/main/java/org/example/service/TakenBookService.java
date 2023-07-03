package org.example.service;

import lombok.Setter;
import org.example.container.ComponentContainer;
import org.example.dto.BookDto;
import org.example.entity.BookEntity;
import org.example.entity.ProfileEntity;
import org.example.entity.TakenBook;
import org.example.enums.StatusBook;
import org.example.mapper.BestBookMapper;
import org.example.mapper.BookHistoryMapper;
import org.example.mapper.BooksOnHandMapper;
import org.example.mapper.TakenBookMapper;
import org.example.repository.BookRepository;
import org.example.repository.ProfileRepository;
import org.example.repository.TakenBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@Setter
public class TakenBookService {
    @Autowired
    private TakenBookRepository takenBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private Scanner scanner;
    public void takeBook() {
        List<BookDto> bookDTOList = bookRepository.getBookList();
        BookEntity bookEntity = getTempBookId(bookDTOList);

        ProfileEntity profileEntity = profileRepository.getProfileById(ComponentContainer.profileDto.getId());
        TakenBook takenBookEntity = new TakenBook();
        takenBookEntity.setProfileEntity(profileEntity);
        takenBookEntity.setBookEntity(bookEntity);
        takenBookEntity.setStatus(StatusBook.TOOK);
        takenBookEntity.setTakenDate(LocalDateTime.now());
        boolean result = takenBookRepository.saveTakenBook(takenBookEntity);
        if (result){
            System.out.println("Taken book saved");
        }else {
            System.err.println("Taken book don't saved");
        }
    }
    public <T> BookEntity getTempBookId(List<T> list){
        BookEntity bookEntity;
        while (true){
            System.out.print("Choose book id -> ");
            int id = scanner.nextInt();
            bookEntity = bookRepository.getBookEntityById(id);
            if (bookEntity == null){
                System.out.println("Wrong selection");
            }else {
                break;
            }
        }
        return bookEntity;
    }

    public void returnBook() {
        List<BooksOnHandMapper> booksOnHandMapperList = takenBookRepository.getBookListByProfileId(ComponentContainer.profileDto.getId());
        BookEntity bookEntity = getTempBookId(booksOnHandMapperList);
        int profileId = ComponentContainer.profileDto.getId();
        int bookId = bookEntity.getId();
        StatusBook takenBookStatus = StatusBook.RETURNED;
        LocalDateTime returnDate = LocalDateTime.now();

        int result = takenBookRepository.returnBook(profileId, bookId, takenBookStatus, returnDate);
        if (result > 0){
            System.out.println("Return book saved");
        }else {
            System.err.println("Return book don't saved");
        }
    }

    public void booksOnHand() {
        List<BooksOnHandMapper> booksOnHandMapperList = takenBookRepository.getBookListByProfileId(ComponentContainer.profileDto.getId());
        booksOnHandMapperList.forEach((n) ->{
            n.setDeadLineDate(n.getTakenDate().plusDays(n.getDeadLine()));
        });
        System.out.println("On service");
        booksOnHandMapperList.forEach(System.out::println);
        if (booksOnHandMapperList.size() == 0){
            System.out.println("List empty");
        }
    }
    public void takeBookHistory() {
        List<BooksOnHandMapper> booksOnHandMapperList = takenBookRepository.getTakenBookHistory(ComponentContainer.profileDto.getId());
        booksOnHandMapperList.forEach(System.out::println);
        if (booksOnHandMapperList.size() == 0){
            System.out.println("Not taken book history yet");
        }
    }
    public void getTakenBooks() {
        List<TakenBookMapper> takenBookMapperList = takenBookRepository.getTakenBookMapperList();
        if (takenBookMapperList.size() == 0){
            System.out.println("Not taken book yet");
        }
        takenBookMapperList.forEach((n) ->{
            n.setDeadLineDate(n.getTakenDate().plusDays(n.getDeadLine()));
        });
        takenBookMapperList.forEach(System.out::println);
    }

    public void getBookHistory() {
        System.out.print("ENTER BOOK ID -> ");
        int id = scanner.nextInt();
        BookEntity bookEntity = bookRepository.getBookEntityById(id);
        if (bookEntity == null){
            System.out.println("Book not found");
            return;
        }
        List<BookHistoryMapper> bookHistoryMapperList = takenBookRepository.getBookHistory(id);
        bookHistoryMapperList.forEach(System.out::println);
    }

    public void getBestBookList() {
        List<Object[]> objects = takenBookRepository.getBestBookList();
        List<BestBookMapper> bestBookMapperList = new ArrayList<>();
//        bestBookMapperList.forEach(System.out::println);
        for (Object[] object : objects) {
            BestBookMapper bestBookMapper = new BestBookMapper();
            bestBookMapper.setId((Integer) object[0]);
            bestBookMapper.setTitle((String) object[1]);
            bestBookMapper.setAuthor((String) object[2]);
            bestBookMapper.setCategoryName( object[3].toString());
            bestBookMapper.setTakenCount((Long) object[4]);
            bestBookMapperList.add(bestBookMapper);
        }

        bestBookMapperList.forEach(System.out::println);
    }
}
