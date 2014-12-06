package GUI;

public class AuthUser {
	public boolean login(String userName, String password) {
		
		String HCusername = "admin";
		String HCpassword = "admin";
		boolean authenticate;

			if (userName == HCusername && password == HCpassword) {
				authenticate = true;
			}

			else
				authenticate = false;

		return authenticate;
	}

}
