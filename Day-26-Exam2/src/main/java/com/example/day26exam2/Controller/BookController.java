package com.example.day26exam2.Controller;

import com.example.day26exam2.ApiResponse.ApiResponse;
import com.example.day26exam2.Model.Book;
import com.example.day26exam2.Model.User;
import com.example.day26exam2.Service.BookService;
import com.example.day26exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService ;
    private final UserService userService;
    // endpoint to get all books
    @GetMapping("/get-all")
    public ResponseEntity getBooks (){
        ArrayList <Book> books = bookService.getBooks();
        return ResponseEntity.status(200).body(books);
    }

    // endpoint to add a new book
    @PostMapping ("/add")
    public ResponseEntity addBook (@RequestBody @Valid Book book, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("book added successfully"));
    }

    // endpoint to update existing book
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook (@PathVariable String id, @RequestBody @Valid Book book, Errors errors){

        if (errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        boolean result = bookService.updateBook(id,book);
        if (result){
            return ResponseEntity.status(200).body(new ApiResponse("book updated successfully"));
        }
        else {
            return ResponseEntity.status(400).body(new ApiResponse("no book with this id was found"));
        }


    }

    // endpoint to delete a book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook (@PathVariable String id){
        boolean result = bookService.deleteBook(id);

        if (result){
            return ResponseEntity.status(200).body(new ApiResponse("book deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("no book with this id was found"));

    }

    // endpoint to return book by name
    @GetMapping("/get-name/{name}")
    public ResponseEntity getBookByName (@PathVariable String name){
        Book b = bookService.getBookByName(name);
        if (b == null){
            return ResponseEntity.status(400).body(new ApiResponse("no book with this name was found"));
        }
        return ResponseEntity.status(200).body(b);
    }

    // endpoint to get books by categories
    @GetMapping("/get-category/{category}")
    public ResponseEntity getBooksByCategory (@PathVariable String category){
        ArrayList <Book> categoryBooks = bookService.getBooksByCategory(category);

        if (categoryBooks == null){
            return ResponseEntity.status(400).body(new ApiResponse("no books with this category was found"));
        }
        return ResponseEntity.status(200).body(categoryBooks);
    }

    // endpoint to get books by pages
    @GetMapping("/get-pages/{pages}")
    public ResponseEntity getBooksByPages (@PathVariable int pages){
        ArrayList <Book> booksByPages = bookService.getBooksByPages(pages);
        if (booksByPages == null){
            return ResponseEntity.status(400).body(new ApiResponse("no books with this number of " +
                    "pages or higher was found"));
        }
        return ResponseEntity.status(200).body(booksByPages);
    }

    // endpoint to change book status to not available
    @PutMapping("/update-available/{userId}/{bookId}")
    public ResponseEntity changeToUnavailable (@PathVariable String userId, @PathVariable String bookId){
        for (User u: userService.getUsers()){
            if (u.getId().equals(userId)){
                if(u.getRole().equals("customer")){
                    return ResponseEntity.status(400).body(new ApiResponse("user is a customer and cant have access"));
                }else {
                    boolean res = bookService.changeToUnavailable(bookId);
                    if (res){
                        return ResponseEntity.status(200).body(new ApiResponse("book status changed successfully"));
                    }else {
                        return ResponseEntity.status(400).body(new ApiResponse("noo book with this id was found"));
                    }
                }
            }
        }
        return ResponseEntity.status(400).body(new ApiResponse("Somthing went Wrong !"));

    }




}
