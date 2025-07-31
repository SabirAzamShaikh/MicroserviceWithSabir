package com.example.Hotel.service.impl;

import com.example.Hotel.dto.HotelRequestdto;
import com.example.Hotel.dto.HotelResponsedto;
import com.example.Hotel.entity.ApiResponse;
import com.example.Hotel.entity.Hotel;
import com.example.Hotel.mapper.HotelMapper;
import com.example.Hotel.repository.HotelRepository;
import com.example.Hotel.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
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

    @Override
    public ApiResponse<String> deleteHotel(int id) {
        try {
            if(repo.existsById(id)){
                repo.deleteById(id);
                return new ApiResponse<>("SUCCESS","Deleted Successfully ","Hotel with ID "+id+" Deleted Successfully");
            }else{
                return new ApiResponse<>("NOT_FOUND","Not found ","Hotel with ID "+id+" is not present");
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage()) ;
        }
}

    public ApiResponse<HotelResponsedto> updateHotel(HotelRequestdto dto,int id) {
    try{
        if(repo.existsById(id)){
            Hotel hotel=repo.findById(id).get();
            hotel.setHotelName(dto.getHotelName());
            hotel.setHotelEmailId(dto.getHotelEmailId());
            hotel.setPhoneNumber(dto.getPhoneNumber());
            hotel.setAddress(dto.getAddress());
            hotel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            Hotel updatedhotel=repo.save(hotel);
        log.info("update hotel: {}",updatedhotel);
      HotelResponsedto resdto=mapper.EntityToDto(updatedhotel);
        return new ApiResponse<>("SUCCESS","Hotel with Hotel ID: "+id+" updated successfully",resdto);
        }
        return null;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage());
    }

    }

}
