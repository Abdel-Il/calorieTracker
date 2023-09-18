package com.example.server.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "user")
    private Set<UserFood> userFood;

    public User(Set<UserFood> userFood) {

        this.userFood = userFood;
    }
    public User(int id, Set<UserFood> userFood) {
        super();
        this.id = id;
        this.userFood = userFood;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userFood=" + userFood +
                '}';
    }
}
