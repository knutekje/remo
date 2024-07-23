package com.example;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import io.micronaut.data.repository.CrudRepository;


@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

    
}