package com.mongo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.mongo.model.Book;
import com.mongo.repo.BookRepository;
import com.mongo.service.Bookservice;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public RedirectView getBooks(){

//		HttpServletResponse response= null;
//		response.sendRedirect("/booknotfound");

		if (repository.findAll().isEmpty()) {
			RedirectView rv = new RedirectView();
			rv.setUrl("/booknotfound");
			return rv;
		}
		//return bookservice.getallbooks();
		RedirectView rv=new RedirectView();
		rv.setUrl("display");
		return rv;
	}

	@GetMapping("/findbyid/{id}")
	public RedirectView getById(@PathVariable("id") int id) throws IOException {
			if (repository.findById(id).isEmpty()) {
				RedirectView rv = new RedirectView();
				rv.setUrl("/booknotfound");
				return rv;
			}

		return new RedirectView("/displaybyid/"+id);// status -- 200
	}

	@GetMapping("/booknotfound")
	public ResponseEntity<String> booknotfound() {
		return new ResponseEntity<>(("The Book is not found"),HttpStatus.NOT_FOUND);// status -- 200
	}

	@PutMapping("update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") int bookno, @RequestBody Book book) {

		return new ResponseEntity<Book>(bookservice.updateBook(book, bookno), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		if(bookservice.getById(id)==null){
			RedirectView rv = new RedirectView();
			rv.setUrl("booknotfound");
		}
		repository.deleteById(id);
		return "data has been deleted : "+id;

	}
}
