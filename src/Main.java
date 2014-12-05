import java.io.IOException;
import java.sql.SQLException;

import model.database.DatabaseInit;
import GUI.GUILogic;
import config.Configurations;

public class Main {
	// Starts public main method.

	public static void main(String[] args) throws SQLException, IOException {
		Configurations cf = new Configurations();

		cf.ReadFile();

		//Creates the database if not exists 
		new DatabaseInit().go();
		
		//Prints database information
		System.out.println(cf.getPassword());
		System.out.println(cf.getHost());
		System.out.println(cf.getPort());
		System.out.println(cf.getUsername());
		System.out.println(cf.getDbname());
		
		//Runs GUI frame
		new GUILogic().run();
		//Runs the TCPServer witch listens for the client
		new TCPServer();

	}

}
