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
@RequestMapping("/hotel")
public class HotelController {
   private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping("/findallhotel")
    public ApiResponse<List<HotelResponsedto>> findAll() {
        try {
            return service.fetchAllHotel();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        }

    @PostMapping("/register")
    public ApiResponse<HotelResponsedto> createHotel(@Valid @RequestBody HotelRequestdto requestdto) {
        try {
            return service.registerHotel(requestdto);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
