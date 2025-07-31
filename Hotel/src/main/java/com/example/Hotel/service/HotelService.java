package com.example.Hotel.service;

import com.example.Hotel.dto.HotelRequestdto;
import com.example.Hotel.dto.HotelResponsedto;
import com.example.Hotel.entity.ApiResponse;

import java.util.List;

public interface HotelService {
public ApiResponse<HotelResponsedto> registerHotel(HotelRequestdto requestdto) throws Exception;
public ApiResponse<List<HotelResponsedto>> fetchAllHotel();
public ApiResponse<String> deleteHotel(int id);
public ApiResponse<HotelResponsedto> updateHotel(HotelRequestdto dto,int id);
}
