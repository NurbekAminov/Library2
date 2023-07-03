package org.example.repository;

import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.example.enums.Role;
import org.example.enums.StatusProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class ProfileRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public Boolean addProfile(ProfileEntity profile){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(profile);
        transaction.commit();
        return true;
    }

    public ProfileEntity getProfileByPhone(String phone) {
        Session session = sessionFactory.openSession();

        Query query = session.createQuery("FROM ProfileEntity where  phone = :p");
        query.setParameter("p", phone);

        return (ProfileEntity) query.getSingleResult();
    }

    public ProfileDto login(String login) {
        Session session = sessionFactory.openSession();

        org.hibernate.query.Query query = session.createQuery("select p.id, p.name, p.surname, p.login, p.password, p.phone, p.statusProfile, p.role, p.createdDate from ProfileEntity as p where p.login = :login");
        query.setParameter("login", login);
        Object[] objects = (Object[]) query.getSingleResult();
        ProfileDto profileDto = new ProfileDto((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (String) objects[4], (String) objects[5], (StatusProfile) objects[6], (Role) objects[7], (LocalDateTime) objects[8]);
        return profileDto;
    }
    public ProfileEntity getProfileById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProfileEntity profileEntity = session.find(ProfileEntity.class,id);
        transaction.commit();
        session.close();
        return profileEntity;
    }
    public List<Object[]> getProfileList() {
        Session session = sessionFactory.openSession();
        List<Object[]> objects;
        try {
            org.hibernate.query.Query query = session.createQuery("select id, name, surname, login, password, phone, status, role, createdDate from ProfileEntity ");
            objects = (List<Object[]>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }finally {
            session.close();
        }
        return objects;
    }

    public List<Object[]> getStudentList() {
        Session session = sessionFactory.openSession();
        org.hibernate.query.Query query = session.createQuery("select p.id, p.name, p.surname, p.login, p.password, p.phone, p.status, p.role, p.createdDate from ProfileEntity as p where p.role = 'STUDENT'");
        return query.getResultList();
    }

    public ProfileEntity searchStudent(String value) {
        Session session = sessionFactory.openSession();
        org.hibernate.query.Query query = session.createQuery("from ProfileEntity as p where p.name = :value or p.surname = :value" +
                " or p.login = :value or p.phone = :value and p.role = 'STUDENT'");
        query.setParameter("value", value);
        ProfileEntity profileEntity = (ProfileEntity) query.getSingleResult();
        session.close();
        return profileEntity;
    }

    public ProfileEntity searchProfile(String value) {
        Session session = sessionFactory.openSession();
        org.hibernate.query.Query query = session.createQuery("from ProfileEntity as p where p.name = :value or p.surname = :value" +
                " or p.login = :value or p.phone = :value");
        query.setParameter("value", value);
        ProfileEntity profileEntity = (ProfileEntity) query.getSingleResult();
        session.close();
        return profileEntity;
    }

    public boolean blockingStudent(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("update ProfileEntity as p set p.status = 'BLOCK' where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public boolean activateStudent(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("update ProfileEntity as p set p.status = 'ACTIVE' where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }

    public List<Object[]> studentByBook(int id) {
        Session session = sessionFactory.openSession();
        NativeQuery query = session.createNativeQuery(
                "select p.id, p.name, p.surname, p.login, p.phone, p.status, tbc.taken_book_count, boh.book_on_hand \n" +
                        "from (select student_id as sid, count(status) as book_on_hand\n" +
                        "from taken_book where status = 'TOOK' and id = :id\n" +
                        "group by student_id) as boh \n" +
                        "inner join (select  student_id as sid, count(*) as taken_book_count\n" +
                        "from taken_book where id = :id\n" +
                        "group by student_id) as tbc \n" +
                        "on boh.sid = tbc.sid\n" +
                        "inner join profile as p on p.id = tbc.sid");
        query.setParameter("id", id);
        List<Object[]> objectList = query.getResultList();
        session.close();
        return objectList;
    }

    public boolean changeStatus(int id, StatusProfile status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        org.hibernate.query.Query query = session.createQuery("update ProfileEntity as p set p.status = :status where p.id = :id");
        query.setParameter("id", id);
        query.setParameter("status", status.toString());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
