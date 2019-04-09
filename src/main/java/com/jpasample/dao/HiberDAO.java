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
        
        //Cat c;
        em.getTransaction().begin();
        em.createQuery("delete from Cat c where c.id>0").executeUpdate();
        em.createQuery("delete from Person c where c.id>0").executeUpdate();
       Person p1 = new Person("Ivan");

        Person p2 = new Person("Oleg");
        em.persist(p2);
        Person p3 = new Person("Petr");
        em.persist(p3);
        Person p4 = new Person("Anton");
        em.persist(p4);
        Person p5 = new Person("Bob");
        em.persist(p5);


        Cat c1 = new Cat("Barsik", 5.0f, p1);
        //em.persist(c1);
        Cat c2 = new Cat("Muska", 2.0f, null);
        em.persist(c2);
        Cat c3 = new Cat("Pushok", 0.5f, null);
        em.persist(c3);
        Cat c4 = new Cat("Tom", 0.6f, p1);
        //em.persist(c4);

        p1.getCats().add(c1);
        p1.getCats().add(c4);
        em.persist(p1);


        c1.setOwner(p2);





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

    public Cat findCatById(long i, EntityManager em) {
        //EntityManager em = emf.createEntityManager();
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

    public Cat findCat(long i) {
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

    public Person findPerson(long i, EntityManager em) {
        //EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select c from Person c where c.id=:paramName ");
        query.setParameter("paramName", i);
        Person res = (Person)query.getSingleResult();
        return res;
    }

    public void deleteCat(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cat c = findCat( id);
        c=em.merge(c);
        em.remove(c);
        em.getTransaction().commit();
        lastStatus = "Кошка удалена!";
    }

    public void deletePerson(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //em.createQuery("delete  from Person p where p.id=:?").setParameter(1,id).executeUpdate();
//                Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :param ");
//                query.setParameter("param", id);
//                int rowsDeleted = query.executeUpdate();
        Person p = findPerson(id, em);
        p=em.merge(p);
        em.remove(p);

        em.getTransaction().commit();
        lastStatus = "Персона удалена!";
    }

    public void changePerson(long pid, long cid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Cat c = findCatById(cid, em) ;
        Person p = findPerson(pid, em);
        System.out.print("Кота " + c + " передаем персоне ");
        System.out.println(p);
        c.setOwner(p);
        //em.createQuery("delete  from Person p where p.id=:?").setParameter(1,id).executeUpdate();
        //Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :param ");
        //query.setParameter("param", id);
        //int rowsDeleted = query.executeUpdate();
        em.getTransaction().commit();
        lastStatus = "Кошка переприсвоена!";
    }

}