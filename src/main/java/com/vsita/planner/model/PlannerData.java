package com.vsita.planner.model;
import javax.persistence.*;

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

    @Id //Indicating the member field below is the primary key of current entity
    @GeneratedValue(strategy = GenerationType.AUTO) //Annotation is used to define generation strategy for the primary key. GenerationType.AUTO means Auto Increment field
    private long id;
    @Column(name = "title")  //@Column annotation is used to define the column in database that maps annotated field.
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "done")
    private boolean done;
    public PlannerData() {
    }
}

