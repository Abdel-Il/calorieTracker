package com.example.server.rest;

import com.example.server.models.Food;
import com.example.server.models.User;
import com.example.server.models.UserFood;
import com.example.server.repo.FoodRepo;
import com.example.server.repo.UserFoodRepo;
import com.example.server.repo.UserRepo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
public class UserFoodController {

    @Autowired
    private UserFoodRepo userFoodRepo;

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping(path = "userFood", produces = "application/json")
    private ResponseEntity<UserFood> addFood(HttpServletRequest request, @RequestBody ObjectNode userFood) {

//        int userId = Integer.parseInt(request.getHeader("Auth"));
        int foodId = userFood.get("food_id").asInt();
        double price = userFood.get("price").asDouble();
        LocalDate date = LocalDate.parse(userFood.get("created_at").asText());

        User user = userRepo.findById(userFood.get("user_id").asInt());
        Food food = foodRepo.findById(foodId);

        UserFood newEntry = new UserFood(date, price, user, food);
        userFoodRepo.save(newEntry);
//        newEntry.setUser(user);
//        System.out.println(userFood);
//        System.out.println(userRepo.findById(userId));
//        System.out.println(foodRepo.findById(id));
//        userFood.setFood(foodRepo.findById(id));
//        userFood.setUser(userRepo.findById(userId));
//        System.out.println(userFood.getUser());

        return ResponseEntity.ok().body(newEntry);
    }

    @GetMapping(path = "userFood/{id}", produces = "application/json")
    private ResponseEntity<List<UserFood>> getUserFoods(@PathVariable int id) {
        return ResponseEntity.ok(userFoodRepo.findUserFoods(id));
    }

//    @DeleteMapping(path = "userFood/{id}")
//    private ResponseEntity<UserFood> deleteUserf
}
