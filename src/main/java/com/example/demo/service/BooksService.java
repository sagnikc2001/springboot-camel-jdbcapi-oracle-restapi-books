package com.example.demo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import oracle.jdbc.OraclePreparedStatement;

import com.example.demo.model.*;

@Component
public class BooksService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ObjectMapper objectMapper;

	public ArrayNode viewBooks() {

		Connection conn = null;
		OraclePreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String queryText = "select * from Books2023";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			rs = pstmt.executeQuery();
			ArrayNode getAllBooksObjectList = objectMapper.createArrayNode();
			while (rs.next()) {
				ObjectNode getAllBooksObject = objectMapper.createObjectNode();
				getAllBooksObject.put("isbn", rs.getInt(1));
				getAllBooksObject.put("bookName", rs.getString(2));
				getAllBooksObject.put("author", rs.getString(3));
				getAllBooksObject.put("genre", rs.getString(4));
				getAllBooksObject.put("qty", rs.getInt(5));
				getAllBooksObjectList.add(getAllBooksObject);
			}
			return getAllBooksObjectList;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public ObjectNode viewBookByIsbn(int isbn) {
		Connection conn = null;
		OraclePreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			String queryText = "select * from books2023 where isbn = ?";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			pstmt.setInt(1, isbn);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				ObjectNode getBookByModelnoObject = objectMapper.createObjectNode();
				getBookByModelnoObject.put("isbn", rs.getInt(1));
				getBookByModelnoObject.put("bookName", rs.getString(2));
				getBookByModelnoObject.put("author", rs.getString(3));
				getBookByModelnoObject.put("genre", rs.getString(4));
				getBookByModelnoObject.put("qty", rs.getInt(5));
				return getBookByModelnoObject;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public void createBook(Books bookData) {

		Connection conn = null;
		OraclePreparedStatement pstmt = null;

		try {

			String queryText = "insert into books2023 (isbn, bookName, author, genre, qty) VALUES (?, ?, ?, ?, ?)";
			conn = dataSource.getConnection();
			pstmt = (OraclePreparedStatement) conn.prepareStatement(queryText);
			pstmt.setInt(1, bookData.getIsbn());
			pstmt.setString(2, bookData.getBookName());
			pstmt.setString(3, bookData.getAuthor());
			pstmt.setString(4, bookData.getGenre());
			pstmt.setInt(5, bookData.getQty());

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Succesfully created book");
			} else {
				System.out.println("Failed in creating book");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
