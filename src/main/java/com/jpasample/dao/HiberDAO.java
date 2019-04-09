package com.jpasample.dao;
import com.jpasample.model.Cat;
import com.jpasample.model.Person;
import java.util.List;
import java.util.Random;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;


@Repository
public class HiberDAO {
    
    // 1--@Autowired
    // 1--EntityManagerFactory emf;

    @PersistenceContext EntityManager em; // --2

    private Random r = new Random();
    private String lastStatus;
    public HiberDAO() {
    }

    @Transactional
    public Cat addRandomCat() {
        // 1--EntityManager em = emf.createEntityManager();
        Cat c = new Cat();
        // 1--em.getTransaction().begin();
        c.setName("Cat"+r.nextInt(100));
        c.setWeight(1.0f+r.nextInt(40)/10.0f);
        em.persist(c);
        // 1--em.getTransaction().commit();
        lastStatus = "Кошка добавлена!";
        return c;
    }
    
    public List<Cat> getAllCats() {
       // 1-- EntityManager em = emf.createEntityManager();
        List<Cat> res = em.createQuery("select c from Cat c",Cat.class).getResultList();
        return res;
    }

    @Transactional
    public List<Person> getAllPersons() {
        // 1--EntityManager em = emf.createEntityManager();
        //2--  List<Person> res = em.createQuery("select p from Person p",Person.class).getResultList();
         List<Person> res = em.createQuery("select p from Person p LEFT JOIN FETCH p.cats",Person.class).getResultList();
//        for (Person p: res ) {
//            p.getCats().size();
//        }


        return res;
    }

    @Transactional
    public void init() {
        // 1--EntityManager em = emf.createEntityManager();
        //em.createQuery("delete from Cat c where c.id>0").executeUpdate();
        //Cat c;
        // 1--em.getTransaction().begin();
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
        // 1--em.getTransaction().commit();
        lastStatus = "Кошки построены!";
    }

    /// проблема с многопоточным доступом! 
    public String pullStatus() {
        return lastStatus;
    }

    public Cat getCatById(int i) {
        return new Cat("??", 0f, null);
    }

    @Transactional
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

    @Transactional
    public Cat findCat(long i) {
        // 1--EntityManager em = emf.createEntityManager();
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

    @Transactional
    public Person findPersonById(long i, EntityManager em) {
        //EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("select c from Person c where c.id=:paramName ");
        query.setParameter("paramName", i);
        Person res = (Person)query.getSingleResult();
        return res;
    }

    @Transactional
    public void deleteCat(long id) {
        // 1--EntityManager em = emf.createEntityManager();
        // 1--em.getTransaction().begin();
        Cat c = findCat( id);
        c=em.merge(c);
        em.remove(c);
        // 1--em.getTransaction().commit();
        lastStatus = "Кошка удалена!";
    }

    @Transactional
    public void deletePerson(long id) {
        // 1--EntityManager em = emf.createEntityManager();
        // 1--em.getTransaction().begin();
        //em.createQuery("delete  from Person p where p.id=:?").setParameter(1,id).executeUpdate();
//                Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :param ");
//                query.setParameter("param", id);
//                int rowsDeleted = query.executeUpdate();
        Person p = findPersonById(id, em);
        p=em.merge(p);
        em.remove(p);

        // 1--em.getTransaction().commit();
        lastStatus = "Персона удалена!";
    }

    @Transactional
    public void changePerson(long pid, long cid) {
        // 1--EntityManager em = emf.createEntityManager();
        // 1-- em.getTransaction().begin();
        Cat c = findCatById(cid, em) ;
        Person p = findPersonById(pid, em);
        System.out.print("Кота " + c + " передаем персоне ");
        System.out.println(p);
        c.setOwner(p);
        //em.createQuery("delete  from Person p where p.id=:?").setParameter(1,id).executeUpdate();
        //Query query = em.createQuery("DELETE FROM Person p WHERE p.id = :param ");
        //query.setParameter("param", id);
        //int rowsDeleted = query.executeUpdate();
        // 1--em.getTransaction().commit();
        lastStatus = "Кошка переприсвоена!";
    }

}