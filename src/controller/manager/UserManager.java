package controller.manager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

import model.User;
import model.dao.UserDao;

public class UserManager {

	private static UserManager instance;

	private UserManager() {
		
	}
	
	public static synchronized UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	
	public boolean register(int userTypeId, 
							String firstName, 
							String lastName, 
							String username, 
							String password, 
							String email, 
							String phone,
							LocalDate registrationDate,
							LocalDateTime lastLogin,
							double money) {
		
		User u = null;
		try {
			u = new User(userTypeId, firstName, lastName, username, password, email, phone, registrationDate, lastLogin, money);
		}catch(InputMismatchException e) {
			System.out.println("Sorry, one of the fields you entered is incorrect");
			System.out.println(e.getMessage());
			return false;
		}
		try {
			UserDao.getInstance().saveUser(u);
			return true;
		} catch (SQLException e) {
			System.out.println("Sorry, the system couldn't save the user into the Database.\n"+e.getMessage());
			return false;
		}
	}

	
}
