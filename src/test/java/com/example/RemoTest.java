package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import jakarta.inject.Inject;

import static io.micronaut.http.HttpHeaders.LOCATION;
import static io.micronaut.http.HttpStatus.BAD_REQUEST;
import static io.micronaut.http.HttpStatus.CREATED;
import static io.micronaut.http.HttpStatus.NOT_FOUND;
import static io.micronaut.http.HttpStatus.NO_CONTENT;

@MicronautTest
class RemoTest {
        private BlockingHttpClient blockingClient;
    @Inject
    @Client("/")
    HttpClient client; 

    @BeforeEach
    void setup() {
        blockingClient = client.toBlocking();
    }

    @Inject
    EmbeddedApplication<?> application;

    

    @Test
    void nonExistingBookReturns404(){
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, () ->
                blockingClient.exchange(HttpRequest.GET("/books/234"))
        );

        assertNotNull(thrown.getResponse());
        assertEquals(NOT_FOUND, thrown.getStatus());
    }


    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }



    @Test
    void testGetBook() {
         Map<String, String> bookData = new HashMap<>();
        bookData.put("title", "The Hitchhiker's Guide to the Galaxy");
        bookData.put("author", "Douglas Adams");
        HttpRequest<Map<String, String>> request = HttpRequest.POST("/books", bookData);
        HttpResponse<Book> response = blockingClient.exchange(request, Book.class);
        assertEquals(CREATED, response.getStatus());
        assertNotNull(response.header(LOCATION));
        Book createdBook = response.body();
        assertNotNull(createdBook);
        assertNotNull(createdBook.getId());
        assertEquals("The Hitchhiker's Guide to the Galaxy", createdBook.getTitle());
        assertEquals("Douglas Adams", createdBook.getAuthor());

}
}
