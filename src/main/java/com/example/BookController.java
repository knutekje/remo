package com.example;

import java.net.URI;
import java.util.List;

import io.micronaut.context.annotation.InjectScope;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import jakarta.validation.Valid;


@Controller("/books")
public class BookController {

   

    @Inject
    protected BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    
    @Get(uri="/", produces="application/json")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
   
    
    @Post(uri="/", produces="application/json")
    public HttpResponse<Book> createBook(@Body @Valid Book book){
        bookRepo.save(book);
        return HttpResponse
                .created(book)
                .headers(headers -> headers.location(toUri(book)));
    }


    private URI toUri(Book book) {
        return URI.create("/books/"+book.getId());
    }

}
    
    
    

    
