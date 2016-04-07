package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Author;
import util.DBConnection;

public class AuthorDTO {
	
	ArrayList<Author> authors = new ArrayList<Author>();
	
	public ArrayList<Author> getAuthors(){
		
		Connection connection = new DBConnection().getConnection();
		Statement statement = null;
	
		try{
			System.out.println("Creating statement for author...");
			statement = connection.createStatement();
			String sql;
			sql = "SELECT * FROM tbl_author";
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()){
				Author author = new Author();
				author.setAuthorId(rs.getInt("authorId"));
				author.setAuthorName(rs.getString("authorName"));
				authors.add(author);
			}
			rs.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return authors;		
	}
	public ResultSet getAuthorById(int authorId) {
		
		Connection connection = new DBConnection().getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String preparedSQL;
			preparedSQL = "SELECT authorId, authorName FROM tbl_author WHERE authorId = ?";
			ps = connection.prepareStatement(preparedSQL);
			ps.setInt(1, authorId);
		    rs = ps.executeQuery();
			rs.close();
		}
		catch (SQLException e){
		e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				ps.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		return rs;
	}
}
