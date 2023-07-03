package org.example.repository;

import lombok.Setter;
import org.example.dto.BookDto;
import org.example.entity.BookEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
@Repository
@Setter
public class BookRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<BookDto> getBookList(){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDto(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where b.visible != false");
        List<BookDto> bookEntityList = query.getResultList();
        session.close();
        return bookEntityList;
    }
    public List<BookDto> searchBook(String value) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDto(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where b.title = :v or b.author = :v or category.name = :v and b.visible != false");
        query.setParameter("v", value);
        List<BookDto> bookEntityList = query.getResultList();
        session.close();
        return bookEntityList;
    }
    public List<BookDto> getBookListByCategory(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.BookDto(b.id, b.title, b.author, b.category.name," +
                " b.publishedDate, b.availableDate, b.visible, b.createdDate) from BookEntity as b where category.id = :id and b.visible != false");
        query.setParameter("id", id);
        List<BookDto> bookDTOList = query.getResultList();
        session.close();
        return bookDTOList;
    }
    public BookEntity getBookEntityById(int id) {
        System.out.println("Get Entity before query");
        Session session = sessionFactory.openSession();
        BookEntity bookEntity = session.find(BookEntity.class, id);
        return bookEntity;
    }
    public boolean addBook(BookEntity bookEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(bookEntity);
        transaction.commit();
        session.close();
        return true;
    }
    public int removeBook(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update BookEntity set visible = false where id = :id");
        query.setParameter("id", id);
        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        return effectedRows;
    }
}
