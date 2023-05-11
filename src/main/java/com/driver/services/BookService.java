package com.driver.services;

import com.driver.models.Author;
import com.driver.models.Book;
import com.driver.repositories.AuthorRepository;
import com.driver.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@Slf4j //for logs
public class BookService {

    @Autowired
    BookRepository bookRepository2;

    public void createBook(Book book){
        bookRepository2.save(book);
    }

    public List<Book> getBooks(String genre, boolean available, String author){
        //i) If genre=”X”, availability = true, and author=null;
        // we require the list of all books which are available and have genre “X”.
        // Note that these books can be written by any author.
        // ii) If genre=”Y”, availability = false, and author=”A”;
        // we require the list of all books which are written by author “A”, have genre “Y”, and are currently unavailable.
        if(genre != null && author != null){
            return bookRepository2.findBooksByGenreAuthor(genre, author, available); //find by genre aud author
        }else if(genre != null){
            return bookRepository2.findBooksByGenre(genre, available); //find by genre
        }else if(author != null){
            return bookRepository2.findBooksByAuthor(author, available); //find by author
        }else{
            return bookRepository2.findByAvailability(available); //find by availability
        }
    }
}
