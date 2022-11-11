package com.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
