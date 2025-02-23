package com.example.Hotel.controller;

import com.example.Hotel.entity.Hotel;
import com.example.Hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController
{
    @Autowired
    private HotelRepository repo;
@GetMapping("/findall")
    public List<Hotel> findAll()
{
    return repo.findAll();
}
@PostMapping("/create")
public Hotel createhotel(@RequestBody Hotel hotel)
{
    return repo.save(hotel);
}
}
