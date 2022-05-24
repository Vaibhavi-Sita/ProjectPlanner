package com.vsita.planner.model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/* adding comments to revise while revisiting my project - VaibhaviSita */

/*
    Spring-ORM (Object Relation Mapping) is a technique
    or a Design Pattern used to access a relational database
    from an object-oriented language.
    covers JPA(Java Persistence API),JDO(Java Data Objects), Hibernate
    source : https://www.geeksforgeeks.org/spring-orm-framework/#:~:text=Spring%2DORM%20is%20a%20technique,Java%20objects%20and%20relational%20databases.
 */

@Entity /* this annotation specifies that the class is an entity
        and is mapped to a database table */

@Table(name="plannerdata") /* allows you to specify the details of the table that will be used to persist the entity in the database
                              https://www.geeksforgeeks.org/spring-data-jpa-table-annotation/
                           */

public class PlannerData {

    //@Id //Indicating the member field below is the primary key of current entity
    //@GeneratedValue(strategy = GenerationType.AUTO) //Annotation is used to define generation strategy for the primary key. GenerationType.AUTO means Auto Increment field
    //private long id;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "title")  //@Column annotation is used to define the column in database that maps annotated field.
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "done")
    private boolean done;
    @Column(name = "lastUpdated")
    private java.sql.Timestamp lastUpdated;

    public PlannerData()
    {

    }
    public PlannerData(String title, String description, boolean done, Timestamp lastUpdated) {

        this.title = title;
        this.description = description;
        this.done = done;
        this.lastUpdated = lastUpdated;
    }

    /*
        Dependency injection is a pattern we can use to implement IoC,
        where the control being inverted is setting an object's dependencies.

        without specifying the implementation of the var

        https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring
     */

    public PlannerData(String title, String description, boolean done)
    {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + ", title=" + title + ", desc=" + description + ", done=" + done + ", lastUpdated=" + lastUpdated + "]";
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}

