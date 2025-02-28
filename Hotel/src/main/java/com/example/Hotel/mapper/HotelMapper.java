package com.example.Hotel.mapper;

import com.example.Hotel.dto.HotelRequestdto;
import com.example.Hotel.dto.HotelResponsedto;
import com.example.Hotel.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel DtoToEntity(HotelRequestdto requestdto)
    {
        Hotel hotel=new Hotel();
        hotel.setHotelName(requestdto.getHotelName());
        hotel.setHotelEmailId(requestdto.getHotelEmailId());
        hotel.setAddress(requestdto.getAddress());
        hotel.setPhoneNumber(requestdto.getPhoneNumber());
        return hotel;
    }
    public HotelResponsedto EntityToDto(Hotel hotel)
    {

        return new HotelResponsedto(hotel.getHotelId(), hotel.getHotelName(), hotel.getHotelEmailId(), hotel.getAddress(), hotel.getPhoneNumber(), hotel.getCreatedAt(),hotel.getUpdatedAt());
    }


}
