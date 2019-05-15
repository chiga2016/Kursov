package com.kursov.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="CATTABLE")
//,uniqueConstraints={@UniqueConstraint(columnNames={"NAME"})}
@EntityListeners(EventMonitor.class)
public class Cat {

    @Id
    @GeneratedValue
    @Column(name="CAT_ID")
    private long id; // identifier
    String name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthdate;
    private float weight;

    //  @Transient
    @ManyToOne(cascade={CascadeType.ALL/*CascadeType.PERSIST*/})
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Cat() {
    }

    public Cat(String name, float weight, Person owner) {
        this.name = name;
        this.weight = weight;
        this.owner= owner;
    }

//    private Set kittens = new HashSet();

    private void setId(Long id) {
        this.id=id;
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdate(Date date) {
        birthdate = date;
    }
    public Date getBirthdate() {
        return birthdate;
    }

     public void setWeight(float weight) {
        this.weight = weight;
    }
    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        String mid;
        return super.toString()+ "{ id=" + id + " name=" + name  + " weight=" + weight + " ownerid="
                ;//+(owner==null?"<none>":owner.getId())+'}';
        //+ " birthdate=" + birthdate
    }
    

}