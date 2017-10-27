package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.User;

@ManagedBean(eager = true)
@SessionScoped
public class UserController {

	private User currentUser = new User();
	private User loggedUser = new User();
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/ip15?characterEncoding=utf8";
	private String username = "root";
	private String password = "";
	private Connection connection = null;
	private PreparedStatement ps = null;
	private String SELECT_LOGIN = "SELECT * FROM users WHERE username = ? AND password = ?";
	
	public UserController() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url,username,password);
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public User getLoggedUser() {
		return loggedUser;
	}
	
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public String login() throws SQLException {
		String retVal = "login.xhtml";
		
		ps = connection.prepareStatement(SELECT_LOGIN);
		ps.setString(1, currentUser.getUsername());
		ps.setString(2, currentUser.getPassword());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			loggedUser.setId(rs.getInt("id"));
			loggedUser.setUsername(rs.getString("username"));
			loggedUser.setPassword(rs.getString("password"));
			loggedUser.setAccountType(rs.getString("accountType"));
			
			if (loggedUser.getAccountType().equalsIgnoreCase("admin")) {
				retVal = "app-admin/index.xhtml";
			}
			else {
				retVal = "app-user/index.xhtml";
			}
		}
		System.out.println("Stanje korisnika: " + retVal);
		return retVal;
	}
}
