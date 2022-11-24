package com.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongo.model.Book;
import com.mongo.repo.BookRepository;
import org.springframework.web.servlet.view.RedirectView;

@Service
public class BookserviceImpl implements Bookservice{
	
	@Autowired
	BookRepository bookRepository;
	
	public BookserviceImpl() {
		super();
	}
	
	public BookserviceImpl(BookRepository bookRepository) {
		super();
		this.bookRepository= bookRepository;
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getallbooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getById(int bookno) {
		Optional<Book> bookcode = bookRepository.findById(bookno);
		return (bookRepository.findById(bookno).orElseThrow(() ->
				new com.mongo.exception.ResourceNotFoundException("Book-Strore", "Book number", bookcode)));
			 // USING LAMBDA

	}

	@Override
	public Book updateBook(Book book, int bookno) {
		// TODO Auto-generated method stub
		Book updatebook = bookRepository.findById(bookno).orElseThrow(() -> // USING LAMBDA
		new com.mongo.exception.ResourceNotFoundException("Book-Strore", "Book number", bookno));
		
				updatebook.setAuthorName(book.getAuthorName());	
				updatebook.setBookName(book.getBookName());
				
				 bookRepository.save(updatebook);
				 return updatebook;
	}

	@Override
	public void deleteBook(int bookno) {
		// TODO Auto-generated method stub
		
	}

}
