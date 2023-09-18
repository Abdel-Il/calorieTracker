package com.example.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_FOOD")
public class UserFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    private double price;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "FOOD_ID")
    private Food food;

    public UserFood() {

    }
    public UserFood(int id, LocalDate createdAt, double price, User user, Food food) {
        super();
        Id = id;
        this.createdAt = createdAt;
        this.price = price;
        this.user = user;
        this.food = food;
    }

    public UserFood(LocalDate createdAt, double price, User user, Food food) {
        this.createdAt = createdAt;
        this.price = price;
        this.user = user;
        this.food = food;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }


    @Override
    public String toString() {
        return "UserFood{" +
                "Id=" + Id +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", user=" + user +
                ", food=" + food +
                '}';
    }
}
