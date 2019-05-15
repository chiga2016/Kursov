package com.kursov.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
//,uniqueConstraints={@UniqueConstraint(columnNames={"NAME"})}
@EntityListeners(EventMonitor.class)
public class Cars {

    @Id
    @GeneratedValue
    @Column(name="car_id")
    private long id; // identifier
    String name;
    String model;
    String korobka;
    //@Temporal(javax.persistence.TemporalType.DATE)
    private String year;

    //  @Transient
    @ManyToOne(cascade={CascadeType.ALL/*CascadeType.PERSIST*/})
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Cars(String name, String model, String korobka, String year) {
        this.name = name;
        this.model = model;
        this.korobka = korobka;
        this.year = year;
    }

    public Cars() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKorobka() {
        return korobka;
    }

    public void setKorobka(String korobka) {
        this.korobka = korobka;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    @Override
    public String toString() {
        String mid;
        return super.toString()+ "{ id=" + id + " name=" + name  + " ownerid="
                ;//+(owner==null?"<none>":owner.getId())+'}';
        //+ " birthdate=" + birthdate
    }


}