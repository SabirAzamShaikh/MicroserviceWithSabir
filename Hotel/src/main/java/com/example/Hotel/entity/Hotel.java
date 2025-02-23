package com.example.Hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Hotel{
@Id
    private int hotelid;
private String hotelname;
private String address;
private String phonenumber;
private LocalDate createdAt=LocalDate.now();
private LocalDate updatedAt;
}
