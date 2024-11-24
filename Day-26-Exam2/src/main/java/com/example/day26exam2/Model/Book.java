package com.example.day26exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "id must be filled")
    @Size(min = 4,message = "id length must be more then 4")
    private String id;

    @NotEmpty(message = "name must not be empty")
    @Size (min = 2,max = 25, message = "book name must be between 2 and 25 ")
    private String name;

    @NotNull(message = "book must have a number of pages")
    @Min(value = 2,message = "Book must have at least 2 pages ")
    @Max(value = 3500, message = "Book pages cannot be more then 3500")
    private int numberOfPages;

    @NotNull(message = "book price cannot be null")
    @Min(value = 1,message = "Book price cannot be less then 1")
    private double price;

    @NotEmpty (message = "book must be assigned to a category")
   @Pattern(regexp = "^(novel|academic)$",message = "book category can only be novel or academic")
    private String category;


    private boolean isAvailable;



}
