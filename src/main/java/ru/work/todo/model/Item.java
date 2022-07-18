package ru.work.todo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;
    private boolean created;
    private boolean done;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreat;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id")
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;

    public Item() {
    }

    public Item(String desc, boolean created, boolean done, Date timeCreat, User user) {
        this.description = desc;
        this.created = created;
        this.done = done;
        this.timeCreat = timeCreat;
        this.user = user;
    }

    public Item(String desc, boolean done, Date timeCreat, User user) {
        this.description = desc;
        this.done = done;
        this.timeCreat = timeCreat;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getTimeCreat() {
        return timeCreat;
    }

    public void setTimeCreat(Date timeCreat) {
        this.timeCreat = timeCreat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("id:'%s', desc: '%s'", id, description);
    }

}
