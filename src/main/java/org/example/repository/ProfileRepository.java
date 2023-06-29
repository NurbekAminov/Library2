package org.example.repository;

import org.example.dto.ProfileDto;
import org.example.entity.ProfileEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
@Component
public class ProfileRepository {
    public Boolean addProfile(ProfileDto profile){
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        session.save(profile);
        t.commit();

        session.close();
        factory.close();
        return true;
    }

    public List<ProfileEntity> getProfileByPhone(String phone) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        Query query = session.createQuery("FROM ProfileEntity where  phone = :p");
        query.setParameter("p", phone);
        List<ProfileEntity> list = query.getResultList();

        session.close();
        factory.close();

        return list;
    }

    public ProfileDto login(String login, String password) {

        return null;
    }
}
