package com.example.day26exam2.Service;

import com.example.day26exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList <User> users = new ArrayList<>();

    // method to get all users
    public ArrayList <User> getUsers (){
        return users;
    }

    // method to add a user
    public void addUser (User user){
        users.add(user);
    }

    // method to update a user if exist
    public boolean updateUser (String id, User user){
        for (User u : users){
            if (u.getId().equals(id)){
                users.set(users.indexOf(u),user);
                return true;
            }
        }
        return false;
    }

    // method to delete user if exist
    public boolean deleteUser (String id){
        for (User u : users){
            if (u.getId().equals(id)){
                users.remove(users.indexOf(u));
                return true;
            }
        }
        return false;
    }

    // method to return the list of users with the balance or higher..
    public ArrayList <User> getUsersWithBalance (double balance){
        ArrayList <User> usersWithBal = new ArrayList<>();
        for (User u : users){
            if (u.getBalance() >= balance){
                usersWithBal.add(u);
            }
        }
        if (usersWithBal.isEmpty()){
            return null;
        }
        return usersWithBal;
    }

    // method to return all the user that are above that age
    public ArrayList <User> getUsersByAge (int age){
        ArrayList <User> usersWithAge = new ArrayList<>();
        for (User u : users){
            if (u.getAge() >= age){
                usersWithAge.add(u);
            }
        }

        if (usersWithAge.isEmpty()){
            return null;
        }
        return usersWithAge;

    }

}
