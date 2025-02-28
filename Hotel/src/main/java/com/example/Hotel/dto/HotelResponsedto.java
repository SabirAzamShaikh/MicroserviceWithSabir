
package com.example.Hotel.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponsedto {
    private int hotelId;
    private String hotelName;
    private String hotelEmailId;
    private String address;
    private String phoneNumber;
    private Timestamp createdAt;
    private Timestamp updatedAt;

}
