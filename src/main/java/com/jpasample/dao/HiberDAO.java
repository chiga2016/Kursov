package com.jpasample.dao;
import com.jpasample.model.Cat;
import com.jpasample.model.Person;
import java.util.List;
import java.util.Random;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;


@Repository
public class HiberDAO {
    
    @Autowired
    EntityManagerFactory emf;
            
    
    
    private Random r = new Random();
    private String lastStatus;
    
    public HiberDAO() {
    }
    
    public Cat addRandomCat() {
        EntityManager em = emf.createEntityManager();
        
        Cat c = new Cat();
        em.getTransaction().begin();
        c.setName("Cat"+r.nextInt(100));
        c.setWeight(1.0f+r.nextInt(40)/10.0f);
        em.persist(c);
        em.getTransaction().commit();
        lastStatus = "Кошка добавлена!";
        return c;
    }
    
    public List<Cat> getAllCats() {
        EntityManager em = emf.createEntityManager();
        List<Cat> res = em.createQuery("select c from Cat c",Cat.class).getResultList();
        
        return res;
    }
    
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        List<Person> res = em.createQuery("select p from Person p",Person.class).getResultList();
        
        return res;
    }

    public void init() {
        EntityManager em = emf.createEntityManager();
        
        //em.createQuery("delete from Cat c where c.id>0").executeUpdate();
        
        Cat c;
        em.getTransaction().begin();
        em.createQuery("delete from Cat c where c.id>0").executeUpdate();
        
        c = new Cat("Barsik", 5.0f, null);
        em.persist(c);
        c = new Cat("Muska", 2.0f, null);
        em.persist(c);
        c = new Cat("Pushok", 0.5f, null);
        em.persist(c);
        
//       Person p = new Person("Ivan");
//       em.persist(p);
        
        em.getTransaction().commit();
        lastStatus = "Кошки построены!";
        
        
    }

    /// проблема с многопоточным доступом! 
    public String pullStatus() {
        return lastStatus;
    }

    public Cat getCatById(int i) {
        return new Cat("??", 0f, null);
    }

    public Cat find(long i) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select c from Cat c where c.id=:paramName ");
        query.setParameter("paramName", i);
        Cat res = (Cat)query.getSingleResult();
        //resultList.forEach(System.out::println);
       // em.close();
       // EntityManager em = emf.createEntityManager();
       // Cat res = em.createQuery("select c from Cat c where c.id=:paramName ",Cat.class).getSingleResult();
       // Query query = session.createQuery("from ContactEntity where firstName = :paramName");
        return res;
    }

    public void remove(Cat c){
        EntityManager em = emf.createEntityManager();
        Query query = em.remove (c); //"select c from Cat c where c.id=:paramName ");
        //query.setParameter("paramName", i);
       // Cat res = (Cat)query.getSingleResult();
    }

}