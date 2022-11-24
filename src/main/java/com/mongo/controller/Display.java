package com.mongo.controller;

import com.mongo.model.Book;
import com.mongo.repo.BookRepository;
import com.mongo.service.Bookservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class Display {
    @Autowired
    private BookRepository repository;

    @Autowired
    private Bookservice bookservice;

    @GetMapping("/display")
    public ResponseEntity<List<Book>> getstatus() {
        if(bookservice.getallbooks().isEmpty()){
            RedirectView rv = new RedirectView();
            rv.setUrl("booknotfound");
            //	return rv;
        }
        return new ResponseEntity<List<Book>>((bookservice.getallbooks()), HttpStatus.ACCEPTED);// status -- 200
        // !=null?bookservice.getallbooks():"Book Id doesnot exist"
    }
    @GetMapping("displaybyid/{id}")
    public ResponseEntity<Book> disp(@PathVariable("id") int id){
        return new ResponseEntity<Book>  ((bookservice.getById(id)), HttpStatus.FOUND);
    }

}
