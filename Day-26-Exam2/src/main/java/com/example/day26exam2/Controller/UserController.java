package com.example.day26exam2.Controller;


import com.example.day26exam2.ApiResponse.ApiResponse;
import com.example.day26exam2.Model.User;
import com.example.day26exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    // endpoint to get all users
    @GetMapping("/get-all")
    public ResponseEntity getUsers (){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    // endpoint to add a user
    @PostMapping("/add")
    public ResponseEntity addUser (@RequestBody @Valid User user, Errors errors){
        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully "));
    }

    // endpoint to update user if exist
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser (@PathVariable String id, @RequestBody @Valid User user, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean result = userService.updateUser(id,user);
        if (result){
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no user with this id was found"));
    }

    // endpoint to delete a user
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser (@PathVariable String id){

        boolean res = userService.deleteUser(id);
        if(res){
            return ResponseEntity.status(200).body(new ApiResponse("user has been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("no uiser with this id has been found"));
        }


    }

    // endpoint to get the users with the balcnce or higher
    @GetMapping("/get-balance/{balance}")
    public ResponseEntity getUsersWithBalance (@PathVariable double balance){
        ArrayList<User> usersBalance = userService.getUsersWithBalance(balance);
        if (usersBalance == null){
            return ResponseEntity.status(400).body(new ApiResponse("no users with this balance was found"));
        }
        return ResponseEntity.status(200).body(usersBalance);
    }


    // end point to get users with the specfied age or higher
    @GetMapping("/get-age/{age}")
    public ResponseEntity getUsersByAge (@PathVariable int age){
        ArrayList <User> usersByAge = userService.getUsersByAge(age);
        if (usersByAge == null){
            return ResponseEntity.status(400).body(new ApiResponse("No users with this age " +
                    "or higher was found"));
        }
        return ResponseEntity.status(200).body(usersByAge);
    }



}
