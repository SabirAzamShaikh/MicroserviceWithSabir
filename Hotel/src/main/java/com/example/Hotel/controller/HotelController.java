package com.example.Hotel.controller;

import com.example.Hotel.entity.Hotel;
import com.example.Hotel.repository.HotelRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private HotelRepository repo;

    @GetMapping("/findall")
    public List<Hotel> findAll() {
        return repo.findAll();
    }

    @PostMapping("/create")
    public Hotel createhotel(@Valid @RequestBody Hotel hotel) {
        try {
            return repo.save(hotel);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
