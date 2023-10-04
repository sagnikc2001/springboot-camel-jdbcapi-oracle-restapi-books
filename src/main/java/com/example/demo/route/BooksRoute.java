package com.example.demo.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.service.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.example.demo.model.*;
@Component
public class BooksRoute extends RouteBuilder {

	@Autowired
	private BooksService booksService;

	@Override
	public void configure() throws Exception {

		restConfiguration().bindingMode(RestBindingMode.auto);

		rest("/books")
//		http://localhost:8080/camel/books/view/all
		 .get("/view/all").to("direct:view-all-books")
//		 http://localhost:8080/camel/books/view/{isbn}
		 .get("/view/{isbn}").to("direct:view-book-by-isbn")
//		 http://localhost:8080/camel/books/add
		 .post("/add").type(Books.class).consumes("application/json").to("direct:add-book");

		
		from("direct:view-all-books").log("something-${body}")
		 .to("bean:booksService?method=viewBooks");

		from("direct:view-book-by-isbn")
		.to("bean:booksService?method=viewBookByIsbn(${header.isbn})");
		
		from("direct:add-book")
         .to("bean:booksService?method=createBook");
	}

}
