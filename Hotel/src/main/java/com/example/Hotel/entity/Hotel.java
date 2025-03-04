package com.example.Hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hotelId;
private String hotelName;
@Email(message = "please enter a valid email id")
private String hotelEmailId;
private String address;
@Size(min=10,max=15,message = "phone number length should be 10-15")
@Pattern(regexp = "^[0-9]+$", message = "Only numbers are allowed")
private String phoneNumber;
private Timestamp createdAt=new Timestamp(System.currentTimeMillis());
private Timestamp updatedAt;
}
