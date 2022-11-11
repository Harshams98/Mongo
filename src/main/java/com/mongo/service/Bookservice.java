package com.mongo.service;

import java.util.List;

import com.mongo.model.Book;

public interface Bookservice {
	
	Book saveBook(Book book);
	
	List<Book> getallbooks();
	
	Book getById (int bookno);
	
	Book updateBook(Book book,int bookno);
	
	void deleteBook(int bookno);
	

}
