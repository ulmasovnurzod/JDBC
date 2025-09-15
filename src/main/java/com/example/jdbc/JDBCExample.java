package com.example.jdbc;

import com.example.jdbc.model.Book;
import com.example.jdbc.service.BookService;

public class JDBCExample {

 static BookService bookService = new BookService();
    public static void main(String[] args) {

//        Book book = Book.builder()
//                .title("biologiya")
//                .pages(120)
//                .build();
//        Integer saveBook = bookService.saveBook(book);
//        System.out.println(saveBook);

        Book updatebook = Book.builder()
                .id(2)
                .title("kimyo")
                .pages(150)
                .build();
        bookService.updateBook(updatebook);

//        bookService.getBooks().forEach(System.out::println);
        System.out.println(bookService.get(21));
    }
}
