package com.codecool.kamilpchelka.checkpoint3.database;

import com.codecool.kamilpchelka.checkpoint3.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BooksDAO extends AbstractDAO {

    public boolean addOrUpdateBook(Book book) {
        String sql = "";
        boolean updateBook = (getBookByISBN(book.getIsbn()) != null);

        if (updateBook) {
            sql = "UPDATE books SET isbn = ?, author = ?, title = ?, publisher = ?, publication_year = ?, price = ?, type = ? WHERE isbn = ?";
        } else {
            sql = "INSERT INTO books (isbn, author, title, publisher, publication_year, price, type) VALUES(?, ?, ?, ?, ?, ?, ?)";
        }

        try (Connection connection = getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, book.getIsbn());
                preparedStatement.setInt(2, book.getAuthor());
                preparedStatement.setString(3, book.getTitle());
                preparedStatement.setInt(4, book.getPublisher());
                preparedStatement.setInt(5, book.getPublicationYear());
                preparedStatement.setDouble(6, book.getPrice());
                preparedStatement.setInt(7, book.getType());

                if (updateBook) preparedStatement.setInt(7, book.getIsbn());

                preparedStatement.executeUpdate();

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Book getBookByISBN(int isbn) {
        String sql = "SELECT * FROM books WHERE isbn = " + isbn;
        Book book = null;
        try (Connection connection = getConnection()) {

            try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {

                book = new Book();

                book.setIsbn(resultSet.getInt(1));
                book.setAuthor(resultSet.getInt(2));
                book.setTitle(resultSet.getString(3));
                book.setPublisher(resultSet.getInt(4));
                book.setPublicationYear(resultSet.getInt(5));
                book.setPrice(resultSet.getDouble(6));
                book.setType(resultSet.getInt(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection()) {

            try (ResultSet resultSet = connection.createStatement().executeQuery(sql)) {

                while (resultSet.next()) {
                    Book book = new Book();

                    book.setIsbn(resultSet.getInt(1));
                    book.setAuthor(resultSet.getInt(2));
                    book.setTitle(resultSet.getString(3));
                    book.setPublisher(resultSet.getInt(4));
                    book.setPublicationYear(resultSet.getInt(5));
                    book.setPrice(resultSet.getDouble(6));
                    book.setType(resultSet.getInt(7));

                    books.add(book);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

}
