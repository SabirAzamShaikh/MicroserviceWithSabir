
package com.example.Hotel.dto;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class HotelResponsedto {
    private int hotelId;
    private String hotelName;
    private String hotelEmailId;
    private String address;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
