package GUI;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Scanner;

import GUI.UserInformation;
import GUI.AuthUser;

import javax.swing.JOptionPane;

import model.QueryBuild.*;
import GUI.Screen;

public class GUILogic {
	private Screen screen;
	private boolean authenticate;

	AuthUser a = new AuthUser();

	public GUILogic() {
		screen = new Screen();

		screen.getLogIn().addActionListener(new LoginActionListener());
		screen.getMainMenu().addActionListener(new MainMenuActionListener());
		screen.getUserInfo().addActionListener(new UserInfoActionListener());
		screen.getNoteList().addActionListener(new NoteListActionListener());
		screen.getUserList().addActionListener(new UserListActionListener());
		screen.getEventlist().addActionListener(new EventListActionListener());
		screen.getAddEventGUI().addActionListener(
				new AddEventGUIActionListener());
		screen.getAddUser().addActionListener(new AddUserActionListener());
		screen.getAddNote().addActionListener(new AddNoteActionListener());
		screen.getCalendar().addActionListener(new CalendarActionListener());

	}

	public void run() {

		screen.show(Screen.LOGIN);
		screen.setVisible(true);
	}

	private class LoginActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {

				String userName = screen.getLogIn().getTextFieldUsername()
						.getText();
				String password = screen.getLogIn().getTextFieldPassword()
						.getText();
				// authenticate = a.login(userName, password);

				if (e.getSource() == screen.getLogIn().getBtnLogIn()) {
					// System.out.println(authenticate);
					// if (authenticate == true) {
					if (userName.equals("admin") && password.equals("admin")) {
						screen.show(Screen.MAINMENU);
						System.out.println("Username and Password correct");
					}
					// else if (authenticate == false) {
					else {
						JOptionPane.showMessageDialog(null,
								"\nPlease enter a valid username & password.",
								"Error message", JOptionPane.PLAIN_MESSAGE);
					}

				}
			} catch (Exception e3) {
			}
		}
	}

	private class MainMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getMainMenu().getBtnLogOut()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getMainMenu().getBtnUserlist()) {
				screen.show(Screen.USERLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnNotelist()) {
				screen.show(Screen.NOTELIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnEventlist()) {
				screen.show(Screen.EVENTLIST);
			}
			if (e.getSource() == screen.getMainMenu().getBtnCalendar()){
				screen.show(Screen.CALENDAR);
			}
		}
	}

	private class AddEventGUIActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddEventGUI().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddEventGUI().getBtnSubmit()) {
				String Type = screen.getAddEventGUI().getTextField_Type()
						.getText();
				String Location = screen.getAddEventGUI()
						.getTextField_Location().getText();
				String Createdby = screen.getAddEventGUI()
						.getTextField_Createdby().getText();
				String start = screen.getAddEventGUI().getTextField_Start()
						.getText();
				String end = screen.getAddEventGUI().getTextField_End()
						.getText();
				String name = screen.getAddEventGUI().getTextField_Name()
						.getText();
				String text = screen.getAddEventGUI().getTextField_Text()
						.getText();

				if (Type.equals("") || Location.equals("")
						|| Createdby.equals("") || start.equals("")
						|| end.equals("") || name.equals("") || text.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				} else {
					QueryBuilder qb = new QueryBuilder();

					String[] kolonner = { "eventid", "type", "location",
							"createdby", "start", "end", "name", "text" };
					String[] Values = { Type, Location, Createdby, start, end,
							name, text };
					try {
						qb.insertInto("events", kolonner).values(Values)
								.ExecuteQuery();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		}
	}

	private class AddNoteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddNote().getBtnAdd()) {
				String Noteadd = screen.getAddNote().getNotefield().getText();

				if (Noteadd.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				}

				else {
					QueryBuilder qb = new QueryBuilder();
					String Text = screen.getAddNote().getNotefield().getText();
					String EventID = screen.getAddNote().getEventTextField()
							.getText();
					// Skal ændres til den nyværende bruger som er logget ind
					String CreatedBy = "Created by";

					String[] kolonner = { "eventid", "createdby", "text" };
					String[] Values = { Text, CreatedBy, EventID };

					try {
						qb.insertInto("notes", kolonner).values(Values)
								.ExecuteQuery();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
		}
	}

	private class AddUserActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getAddUser().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getAddUser().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getAddUser().getBtnSubmit()) {
				String Email = screen.getAddUser().getTextField_Email()
						.getText();
				String Type = screen.getAddUser().getTextField_Type().getText();
				String Password = screen.getAddUser().getTextField_Password()
						.getText();

				if (Email.equals("") || Type.equals("") || Password.equals("")) {
					JOptionPane.showMessageDialog(null,
							"\nPlease fill out all the fields",
							"Error message", JOptionPane.PLAIN_MESSAGE);
				} else {
					QueryBuilder qb = new QueryBuilder();

					String[] kolonner = { "email", "password" };
					String[] Values = { Email, Password };

					String[] kolonner2 = { "type" };
					String[] Values2 = { Type };
					try {
						qb.insertInto("users", kolonner).values(Values)
								.Execute();
						qb.insertInto("roles", kolonner2).values(Values2)
								.Execute();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		}
	}

	private class UserInfoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getUserInfo().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserInfo().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserInfo().getBtnSubmit()) {

			}
		}
	}
	
	private class CalendarActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == screen.getCalendar().getBtnMainMenu()){
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getCalendar().getBtnLogout()){
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getCalendar().getBtnDelete()){
				QueryBuilder qb = new QueryBuilder();
				String idDelete = (String) screen
						.getCalendar()
						.getTable()
						.getValueAt(
								screen.getCalendar().getTable()
										.getSelectedRow(), 1);
				int inputvalue = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete?" + idDelete,
						"WARNING", inputvalue);
				if (inputvalue == JOptionPane.YES_OPTION) {
					try {
						qb.deleteFrom("calendar").where("CalendarID", "=", idDelete)
								.Execute();
						System.out.println("Event deleted with EventID "
								+ idDelete);
					} catch (SQLException e1) {
						System.out.println("Event not deleted");
						e1.printStackTrace();
					}
					if (inputvalue == JOptionPane.NO_OPTION) {

					}
				}
			}
			if (e.getSource() == screen.getCalendar().getButtontAdd()){
				
			}
		}
	}
	private class NoteListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getNoteList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getNoteList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
		}
	}

	private class UserListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getUserList().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getUserList().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getUserList().getButtontAdd()) {
				screen.show(Screen.ADDUSER);

			}
			if (e.getSource() == screen.getUserList().getBtnDelete()) {
				QueryBuilder qb = new QueryBuilder();
				String h = (String) screen
						.getUserList()
						.getTable()
						.getValueAt(
								screen.getUserList().getTable()
										.getSelectedRow(), 0);
				// String inputvalue =
				// JOptionPane.showInputDialog("Write the user email you want to delete");
				try {
					qb.deleteFrom("users").where("userid", "=", h).Execute();
					System.out.println("User deleted with UserID " + h);
				} catch (SQLException e1) {
					System.out.println("User not deleted");
					e1.printStackTrace();
				}
			}

		}
	}

	private class EventListActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == screen.getEventlist().getBtnMainMenu()) {
				screen.show(Screen.MAINMENU);
			}
			if (e.getSource() == screen.getEventlist().getBtnLogout()) {
				screen.show(Screen.LOGIN);
			}
			if (e.getSource() == screen.getEventlist().getBtnAdd()) {
				screen.show(Screen.ADDEVENTGUI);
			}
			if (e.getSource() == screen.getEventlist().getBtnDelete()) {
				QueryBuilder qb = new QueryBuilder();
				String idDelete = (String) screen
						.getEventlist()
						.getTable()
						.getValueAt(
								screen.getEventlist().getTable()
										.getSelectedRow(), 0);
				int inputvalue = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete?" + idDelete,
						"WARNING", inputvalue);
				if (inputvalue == JOptionPane.YES_OPTION) {
					try {
						qb.deleteFrom("events").where("eventid", "=", idDelete)
								.Execute();
						System.out.println("Event deleted with EventID "
								+ idDelete);
					} catch (SQLException e1) {
						System.out.println("Event not deleted");
						e1.printStackTrace();
					}
					if (inputvalue == JOptionPane.NO_OPTION) {

					}
				}
			}
		}
	}

}
