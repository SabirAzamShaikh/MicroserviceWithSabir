package com.example.Hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
private String Status;
private String ResponseMessage;
private T data;
}
