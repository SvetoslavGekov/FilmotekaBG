package user;

public class Administrator extends User implements IAdministrator {

	public Administrator(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}

}
