package com.example.server.rest;

import com.example.server.models.Food;
import com.example.server.repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class FoodController {

    @Autowired
    private FoodRepo foodRepo;

    @GetMapping(path = "food", produces = "application/json")
    public List<Food> findAll() {
        return foodRepo.findAll();
    }

    @GetMapping(path = "food/{id}", produces = "application/json")
    public Food findById(@PathVariable int id) {
        return foodRepo.findById(id);
    }
}
