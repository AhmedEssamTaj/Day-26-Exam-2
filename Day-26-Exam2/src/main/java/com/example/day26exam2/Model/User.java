package com.example.day26exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "user id cannot be empty")
    @Size(min = 4,message = "id must be at least 4")
    private String id;

    @NotEmpty(message = "user name cannot be empty")
    @Size(min = 2,max = 25,message = "username must be between 2 and 25")
    private String name;

    @NotNull(message = "user age cannot be null")
    @Min(value = 6,message = "user must be 6 years or older")
    @Max(value = 100, message = "user cannot be older then 100 years old")
    private int age;

    @NotNull(message = "balance cannot be empty")
    @Min(value = 0,message = "balance cannot be less then 0")
    private double balance;

    @NotEmpty(message = "user must have a role")
    @Pattern(regexp = "^(customer|libraian)$",message = "user must be either customer or libraian ")
    private String role;


}
