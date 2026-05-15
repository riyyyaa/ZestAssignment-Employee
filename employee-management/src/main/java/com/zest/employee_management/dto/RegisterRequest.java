package com.zest.employee_management.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
}