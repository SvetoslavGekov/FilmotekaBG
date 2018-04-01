package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import dbManager.DBManager;
import model.User;


public class UserDao implements IUserDao{

	private static UserDao instance;
	private Connection connection;
	
	
	private UserDao() {
		connection = DBManager.getInstance().getCon();
	}
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	@Override
	public User getUserByID(int id) throws SQLException {
		User user = null;
		String sql = "SELECT user_id, user_type_id, first_name, last_name, username, password, email, phone, registration_date, last_login, money FROM users WHERE user_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setInt(1, id);
			try(ResultSet rs = ps.executeQuery();){
				rs.next();
				user = new User(rs.getInt("user_id"),
								rs.getInt("user_type_id"),
								rs.getString("first_name"),
								rs.getString("last_name"),
								rs.getString("username"),
								rs.getString("password"),
								rs.getString("email"),
								rs.getString("phone"),
								rs.getDate("registration_date").toLocalDate(),
								rs.getTimestamp("last_login").toLocalDateTime(),
								rs.getDouble("money")
						);

			}
		}
		return user;
	}
	
	

	@Override
	public void saveUser(User user) throws SQLException {
		PreparedStatement s = connection.prepareStatement("INSERT INTO users (user_type_id, first_name, last_name, username, email, password, phone, registration_date, last_login, money) VALUES (?,?,?,?,?,?,?,?,?,?);");
		s.setInt(1, user.getUserTypeId());
		s.setString(2, user.getFirstName());
		s.setString(3, user.getLastName());
		s.setString(4, user.getUsername());
		s.setString(5, user.getEmail());
		s.setString(6, user.getPassword());
		s.setString(7, user.getPhone());
		s.setDate(8, Date.valueOf(user.getRegisrtationDate()));
		s.setTimestamp(9, Timestamp.valueOf(user.getLastLogin()));
		s.setDouble(10, user.getMoney());
		s.executeUpdate();
		
		System.out.println("Successfully saved user to database.");
	}

	@Override
	public void updateUser(User user) throws SQLException {
		String sqlQuery = "UPDATE users SET username = ?, email = ?, password = ?, first_name = ?, last_name = ?, phone = ?, last_login = ?, profile_picture = ?, money = ? WHERE user_id = ?;";
		try(PreparedStatement ps = connection.prepareStatement(sqlQuery)){
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFirstName());
			ps.setString(5, user.getLastName());
			ps.setString(6, user.getPhone());
			ps.setTimestamp(7, Timestamp.valueOf(user.getLastLogin()));
			ps.setString(8, user.getProfilePicture());
			ps.setDouble(9, user.getMoney());
			ps.setInt(10, user.getUserId());
			ps.executeUpdate();
		}
		System.out.println("Successfully updated user in database.");
	}
	

	@Override
	public void deleteUser(User user) throws SQLException {
		String sqlQuery = "DELETE FROM users WHERE username = ?;";
		try (PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
			ps.setString(1, user.getUsername());
			ps.executeUpdate();
		}
		System.out.println("Successfully deleted account from database.");
	}

	@Override
	public Collection<User> getAllUsers() throws SQLException {
		HashSet<User> resultUsers = new HashSet<>();
		String sql = "SELECT user_id, user_type_id, username, email, password, first_name, last_name, registration_date, phone, last_login, profile_picture, money FROM users ORDER BY user_id DESC;";
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery();) {
				while(rs.next()) {
					User user = new User(rs.getInt("user_id"),
							rs.getInt("user_type_id"),
							rs.getString("first_name"),
							rs.getString("last_name"),
							rs.getString("username"),
							rs.getString("password"),
							rs.getString("email"),
							rs.getString("phone"),
							rs.getDate("registration_date").toLocalDate(),
							rs.getTimestamp("last_login").toLocalDateTime(),
							rs.getDouble("money")
							);
					
					resultUsers.add(user);
				}
			}
		}
		if(resultUsers.isEmpty()) {
			return Collections.emptyList();
		}
		return resultUsers;
	}
}
