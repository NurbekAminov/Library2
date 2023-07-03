package org.example.repository;

import lombok.Setter;
import org.example.entity.TakenBook;
import org.example.enums.StatusBook;
import org.example.mapper.BookHistoryMapper;
import org.example.mapper.BooksOnHandMapper;
import org.example.mapper.TakenBookMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Setter
public class TakenBookRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public boolean saveTakenBook(TakenBook takenBookEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(takenBookEntity);
        transaction.commit();
        session.close();
        return true;
    }

    public List<BooksOnHandMapper> getBookListByProfileId(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "select new org.example.mapper.BooksOnHandMapper(b.id, b.title, b.author, b.category.name," +
                        " t.takenDate, b.availableDate) from TakenBook as t inner join t.bookEntity as b " +
                        "inner join t.profileEntity as p where p.id = :id and t.status = 'TOOK'");
        query.setParameter("id", id);
        List<BooksOnHandMapper> booksOnHandMapperList = query.getResultList();
        session.close();
        return booksOnHandMapperList;
    }
    public int returnBook(int profileId, int bookId, StatusBook takenBookStatus, LocalDateTime returnDate) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        System.out.println("Repo before query");
        Query query = session.createQuery(
                "update TakenBook as t set returnDate = :date," +
                        "status = :status where t.profileEntity = :profile_id and t.bookEntity = :book_id");
        query.setParameter("date",returnDate);
        query.setParameter("status",takenBookStatus.toString());
        query.setParameter("profile_id",profileId);
        query.setParameter("book_id",bookId);
        int effectedRows = query.executeUpdate();
        System.out.println("Repo before query");
        transaction.commit();
        session.close();
        return effectedRows;
    }
    public List<BooksOnHandMapper> getTakenBookHistory(Integer id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(
                "select new org.example.mapper.BooksOnHandMapper(b.id, b.title, b.author, b.category.name," +
                        " t.takenDate, t.returnDate) from TakenBook as t inner join t.bookEntity as b " +
                        "inner join t.profileEntity as p where p.id = :id");
        query.setParameter("id", id);
        List<BooksOnHandMapper> booksOnHandMapperList = query.getResultList();
        session.close();
        return booksOnHandMapperList;
    }
    public List<TakenBookMapper> getTakenBookMapperList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.mapper.TakenBookMapper(b.id, b.title," +
                " b.author, b.category.name, t.takenDate, b.availableDate, p.name, p.surname, p.phone) " +
                "from TakenBookEntity as t inner join t.bookEntity as b inner join t.profileEntity as p" +
                " where t.status = 'TOOK'");
        List<TakenBookMapper> takenBookMapper = query.getResultList();
        session.close();
        return takenBookMapper;
    }

    public List<BookHistoryMapper> getBookHistory(int id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.mapper.BookHistoryMapper(t.takenDate," +
                "t.returnDate, p.name, p.surname, p.phone, t.note) from TakenBookEntity as t inner join t.bookEntity as b " +
                "inner join t.profileEntity as p where b.id = :id");
        query.setParameter("id", id);
        List<BookHistoryMapper> bookHistoryMapper = query.getResultList();
        session.close();
        return bookHistoryMapper;
    }

    public List<Object[]> getBestBookList() {
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery("select b.id, b.title, b.author, b.category_id, taken_count \n" +
                "from(select book_id, count(*) as taken_count from taken_book\n" +
                "group by book_id) as temp\n" +
                "inner join book as b on temp.book_id = b.id\n" +
                "order by taken_count desc");

        List<Object[]> bestBookMapperList = query.getResultList();
        session.close();
//        bestBookMapperList.forEach(System.out::println);
        return bestBookMapperList;
    }
}
