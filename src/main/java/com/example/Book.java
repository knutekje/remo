package com.example;



import io.micronaut.core.annotation.*;
import io.micronaut.serde.annotation.SerdeImport;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;




@jakarta.persistence.Entity
@Introspected
@Table(name="books")
@Serdeable.Serializable
@SerdeImport(Book.class)
@Serdeable.Deserializable
public class Book {
    
    public Book(long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

   
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="author")
    private String author;

    public long getId() {
        return id;
    }
    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + title + 
                ", author='" + author + 
                '}';
    }

}
