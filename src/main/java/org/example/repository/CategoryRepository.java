package org.example.repository;

import lombok.Setter;
import org.example.dto.CategoryDto;
import org.example.entity.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
@Repository
@Setter
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;
    public List<CategoryDto> getCategoryList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select new org.example.dto.CategoryDto(c.id , c.name, c.visible) from CategoryEntity as c");
        return (List<CategoryDto>) query.getResultList();
    }

    public CategoryEntity getCategoryByName(String categoryName) {
        Session session = sessionFactory.openSession();
        CategoryEntity categoryEntity;
        try {
            Query query = session.createQuery("from CategoryEntity where name = :name");
            query.setParameter("name", categoryName);
            categoryEntity = (CategoryEntity) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }finally {
            session.close();
        }
        return categoryEntity;
    }

    public boolean addCategory(CategoryEntity categoryEntity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(categoryEntity);
        transaction.commit();
        session.close();
        return true;
    }

    public CategoryEntity getCategoryById(int id) {
        Session session = sessionFactory.openSession();
        CategoryEntity categoryEntity = session.find(CategoryEntity.class,id);
        session.close();
        return categoryEntity;
    }

    public int changeVisibleCategory(int id, boolean b) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update CategoryEntity set visible = :b where id = :id");
        query.setParameter("id", id);
        query.setParameter("b", b);
        int effectedRows = query.executeUpdate();
        transaction.commit();
        session.close();
        return effectedRows;
    }

}
