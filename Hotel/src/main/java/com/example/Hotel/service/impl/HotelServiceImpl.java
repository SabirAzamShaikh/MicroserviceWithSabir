package com.example.Hotel.service.impl;

import com.example.Hotel.dto.HotelRequestdto;
import com.example.Hotel.dto.HotelResponsedto;
import com.example.Hotel.entity.ApiResponse;
import com.example.Hotel.entity.Hotel;
import com.example.Hotel.mapper.HotelMapper;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.service.HotelService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
   private final HotelMapper mapper;
    private final HotelRepository repo;

    public HotelServiceImpl(HotelMapper mapper, HotelRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    public ApiResponse<HotelResponsedto> registerHotel(HotelRequestdto requestdto) throws Exception {
    try {
        Hotel hotel = mapper.DtoToEntity(requestdto);
        Hotel saveHotel = repo.save(hotel);
        HotelResponsedto responsedto = mapper.EntityToDto(saveHotel);
        return new ApiResponse<>("CREATED", "Hotel Created Succesfully", responsedto);
    }catch(DataAccessException e){
        throw new RuntimeException(e.getMessage());
    }
    catch (Exception e){
        throw new Exception(e.getMessage()) ;
    }
    }

public ApiResponse<List<HotelResponsedto>> fetchAllHotel() {
    List<Hotel> hotelList=repo.findAll();
        List<HotelResponsedto> responseList = hotelList.stream()
                .map(mapper::EntityToDto)
                .toList();
        return new ApiResponse<>("OK","Data fetched Succesfully",responseList);
    }

}
