package com.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.mongo.model.Book;
import com.mongo.repo.BookRepository;
import com.mongo.service.Bookservice;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository repository;

	@Autowired
	private Bookservice bookservice;
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		bookservice.saveBook(book);
		return "Book inserted :"+book.getBookName();
	
	}
	
	@GetMapping("/findall")
	public List<Book> getBooks(){
		return bookservice.getallbooks();
	}
		
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<String> getById(@PathVariable("id") int id) {
		return new ResponseEntity<String>((bookservice.getById(id)!=null?bookservice.getById(id).toString():"Book Id doesnot exist"), HttpStatus.ACCEPTED);// status -- 200
	}
	

	@PutMapping("update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") int bookno, @RequestBody Book book) {
		return new ResponseEntity<Book>(bookservice.updateBook(book, bookno), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "data deleted : "+id;
	
	}
}
