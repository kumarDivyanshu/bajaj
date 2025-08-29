package com.bajaj.BajajTask.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequest {
    private String name;
    private String regNo;
    private String email;
}
