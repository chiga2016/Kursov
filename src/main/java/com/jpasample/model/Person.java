package com.jpasample.model;

import java.util.*;
import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;

    @Column(name="FIO")
    private String name;

    
    //private List<Food> food = new ArrayList<Food>();

    public Person() { 
       // убрать - нарушает соотвтетсвтие базе
       // cats.add(new Cat("?",1f, this));
    }
    
    public Person(String name) {
        this.name = name;
        
        //cats.add(new Cat("?",1f, this));
    }

    @OneToMany(mappedBy="owner",cascade={CascadeType.ALL /*CascadeType.PERSIST*/}) // НОВЫХ элементов в БД обратная сторона связи не добавляет!
    //@OneToMany // осторожно - это независимая связь, а не другой конец @ManyToOne! порождает доп. таблицу
    //   private Set<Cat> cats = new HashSet<Cat>(); // без номера и порядка!
    //@OrderBy(value="name desc")
    //@Transient
    private List<Cat> cats = new ArrayList<Cat>();     // c номером и порядком!
    
    
    //private Set<Cat> cats = new HashSet<Cat>();     // без номера и порядка!

    
    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
    
    public String getCatIds() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cats: ");
        for (Cat c : cats) {
            sb.append(c.getId());
            sb.append(',');
        }
             
        return sb.toString();
    }
    
    // Неизвестно, как отображать в БД
    // private Map<String,Cat> catMap = new HashMap<String,Cat>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString()+" {" + "id=" + id + " name=" + name + '}';
    }
    

}
