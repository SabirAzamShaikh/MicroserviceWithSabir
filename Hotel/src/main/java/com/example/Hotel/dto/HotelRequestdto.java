package com.example.Hotel.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class HotelRequestdto {

    @NotBlank
    private String hotelName;
    @Email(message = "please enter a valid email id")
    private String hotelEmailId;
    @NotBlank
    private String address;
    @Size(min=10,max=15,message = "phone number length should be 10-15")
    @Pattern(regexp = "^[0-9]+$", message = "Only numbers are allowed")
    private String phoneNumber;
}
