package com.example.jdbc.service;

import com.example.jdbc.database.DatabaseConnection;
import com.example.jdbc.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    public Integer saveBook(Book book) {
        String sql = "INSERT INTO book(title, pages) VALUES(?, ?) RETURNING id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getPages());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateBook(Book book) {


        String sql = "UPDATE book SET pages = ? , title = ? WHERE id = ? ";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, book.getPages());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM book";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .pages(resultSet.getInt("pages"))
                        .build();
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public Book get(Integer id) {
        String sql = "SELECT * FROM book where id = ?";

       try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1,id);
           ResultSet resultSet = preparedStatement.executeQuery();
           if (resultSet.next()){
               Book book = Book.builder()
                       .id(resultSet.getInt("id"))
                       .title(resultSet.getString("title"))
                       .pages(resultSet.getInt("pages"))
                       .build();

               return book;
           }
           return null;


       } catch (SQLException e) {
           throw new RuntimeException(e);
       }


    }


}
