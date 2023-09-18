package com.example.server.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "FOOD")
public class Food {
    @Id
    @GeneratedValue
    @Column(name = "FOOD_ID")
    private int id;
    private String name;
    private int calorieCount;

    @OneToMany(mappedBy = "food")
    private Set<UserFood> userFood;

    public Food(Set<UserFood> userFood) {

        this.userFood = userFood;
    }

    public Food(int id, String name, int calorieCount, Set<UserFood> userFood) {
        super();
        this.id = id;
        this.name = name;
        this.calorieCount = calorieCount;
        this.userFood = userFood;
    }

    public Food() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorieCount() {
        return calorieCount;
    }

    public void setCalorieCount(int calorieCount) {
        this.calorieCount = calorieCount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calorieCount=" + calorieCount +
                ", userFood=" + userFood +
                '}';
    }
}
