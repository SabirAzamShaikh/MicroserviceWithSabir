package com.example.Hotel.controller;

import com.example.Hotel.dto.HotelRequestdto;
import com.example.Hotel.dto.HotelResponsedto;
import com.example.Hotel.entity.ApiResponse;
import com.example.Hotel.entity.Hotel;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {
    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping("/fetchall")
    public ApiResponse<List<HotelResponsedto>> findAll() {
        try {
            return service.fetchAllHotel();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ApiResponse<HotelResponsedto> createHotel(@Valid @RequestBody HotelRequestdto requestdto) {
        try {
            return service.registerHotel(requestdto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteHotel(@PathVariable int id) {
        try {
            return service.deleteHotel(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ApiResponse<HotelResponsedto> updateHotel(@Valid @RequestBody HotelRequestdto dto, @PathVariable int id) {
        try {
     return  service.updateHotel(dto,id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}