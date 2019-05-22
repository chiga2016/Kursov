package com.kursov.model;

import java.util.*;
import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;

    public Person(String fam, String name, String ot) {
        this.fam = fam;
        this.name = name;
        this.ot = ot;
    }

    public String getFam() {
        return fam;
    }

    public void setFam(String fam) {
        this.fam = fam;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    @Column(name="fam")
    private String fam;

    @Column(name="name")
    private String name;

    public Person(String fam, String name, String ot, String dr, String vuNumber) {
        this.fam = fam;
        this.name = name;
        this.ot = ot;
        this.vuNumber = vuNumber;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    @Column(name="ot")
    private String ot;

    @Column(name="dr")
    private String dr;

    public String getVuNumber() {
        return vuNumber;
    }

    public void setVuNumber(String vuNumber) {
        this.vuNumber = vuNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="vuNumber")
    private String vuNumber;

    @Column(name="phone")
    private String phone;

    public Person() {
    }
    
    public Person(String name) {
        this.name = name;
        
        //cats.add(new Cat("?",1f, this));
    }

    @OneToMany(mappedBy="owner"/*, fetch = FetchType.EAGER*/, cascade={CascadeType.ALL, /*CascadeType.PERSIST*/}) // НОВЫХ элементов в БД обратная сторона связи не добавляет!
    //@OneToMany // осторожно - это независимая связь, а не другой конец @ManyToOne! порождает доп. таблицу
    //   private Set<Cat> cats = new HashSet<Cat>(); // без номера и порядка!
    //@OrderBy(value="name desc")
    //@Transient
    private List<Cars> cars = new ArrayList<Cars>();     // c номером и порядком
    
    //private Set<Cat> cats = new HashSet<Cat>();     // без номера и порядка!

    public List<Cars> getCars() {
        return cars;
    }

    public void setCars(List<Cars> cars) {
        this.cars = cars;
    }
    
    public String getCarIds() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cars: ");
        for (Cars c : cars) {
            sb.append(c.getId());
            sb.append(',');
        }
        return sb.toString();
    }

    public String getCarNames() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cars: ");
        for (Cars c : cars) {
            sb.append(c.getName());
            sb.append(',');
        }
        return sb.toString();
    }
    
    // Неизвестно, как отображать в БД

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
