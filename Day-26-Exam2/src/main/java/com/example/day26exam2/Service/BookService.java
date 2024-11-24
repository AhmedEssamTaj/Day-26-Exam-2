package com.example.day26exam2.Service;

import com.example.day26exam2.Model.Book;
import com.example.day26exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {


    ArrayList <Book> books = new ArrayList<>();


    // method to get all books
    public ArrayList <Book> getBooks (){
        return books;
    }

    // method to add a new book
    public void addBook (Book book){
        books.add(book);
    }

    // method to update a book if it exist
    public boolean updateBook (String id, Book book){

        for (Book b : books){
            if (b.getId().equals(id)){
                books.set(books.indexOf(b),book);
                return true;
            }
        }
        return false;
    }

    // method to delete a book if exist..
    public boolean deleteBook (String id ){
        for (Book b : books ){
            if (b.getId().equals(id)){
                books.remove(books.indexOf(b));
                return true;
            }
        }

        return false;
    }

    // method to return a book by his name
    public Book getBookByName (String name){

        for (Book b : books){
            if (b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }

    // method to get books by category
    public ArrayList <Book> getBooksByCategory (String category){
        ArrayList <Book> categoryBook= new ArrayList<>();

        for (Book b: books){
            if (b.getCategory().equals(category)){
                categoryBook.add(b);
            }
        }
        if (categoryBook.isEmpty()){
            return null;
        }
        return categoryBook;

    }

    // method to return books with this number of pages or higher
    public ArrayList <Book> getBooksByPages (int pages) {
        ArrayList<Book> booksByPages = new ArrayList<>();
        for (Book b: books){
            if (b.getNumberOfPages()>= pages){
                booksByPages.add(b);
            }
        }
        if (booksByPages.isEmpty()){
            return null;
        }
        return booksByPages;
    }

    // method to change the status of the book to unavialbale
    public boolean changeToUnavailable (String bookId){

    for (Book b: books){
        if (b.getId().equals(bookId)){
            books.get(books.indexOf(b)).setAvailable(false);
            return true;
        }
    }
    return false;

    }
}
