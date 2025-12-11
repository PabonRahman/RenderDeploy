package com.spring_security_app.dto;


import lombok.Data;

@Data
public class StudentDto {

    private String studentName;

    private String address;

    private Float semisterCost;

    private Integer semisterNo;
}
