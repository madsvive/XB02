package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

public class Screen extends JFrame {

// EXIT_ON_CLOSE er ændret til HIDE_ON_CLOSE ..
	
	public static final String LOGIN = "name_276091497157488";
	public static final String MAINMENU = "name_276416022878030";
	public static final String USERINFO = "name_277892826656058";
	public static final String ADDUSERGUI = "name_278604525733268";
	public static final String NOTELIST = "name_278522430661848";
	public static final String USERLIST = "name_280161954000083";
	public static final String EVENTLIST = "name_534038022095149";
	public static final String ADDEVENTGUI = "name_6308445225625";
	public static final String ADDUSER = "name_10334207821613";
	public static final String CALENDAR = "name_calendar";
	
	private JPanel contentPane;
	private final LogIn logIn = new LogIn();
	private final MainMenu mainMenu = new MainMenu();
	private final UserInfo userInfo = new UserInfo();
	private final NoteList noteList = new NoteList();
	private final UserList userlist = new UserList();
	CardLayout c;
	private final EventList eventList = new EventList();
	private AddEventGUI addEventGUI;
	private final AddUser addUser = new AddUser();
	private final AddNote addNote = new AddNote();
	private final Calendar calendar = new Calendar();

	public Screen() {
		setTitle("Doek4life");
		setResizable(false);
		setBounds(100, 100, 1366, 768);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		contentPane.add(addUser, "name_10334207821613");
		
		addEventGUI = new AddEventGUI();
		contentPane.add(addEventGUI, "name_6308445225625");
		
		contentPane.add(logIn, "name_276091497157488");
		
		contentPane.add(mainMenu, "name_276416022878030");
		
		contentPane.add(userInfo, "name_277892826656058");
		
		contentPane.add(noteList, "name_278522430661848");
		
		contentPane.add(eventList, "name_534038022095149");
		
		contentPane.add(userlist, "name_280161954000083");
		contentPane.add(calendar, "name_calendar");
		c = (CardLayout) getContentPane().getLayout();
	}
	
	public LogIn getLogIn() {
		return logIn;
	}
	
	public MainMenu getMainMenu() {
		return mainMenu;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public NoteList getNoteList() {
		return noteList;
	}
	public UserList getUserList() {
		return userlist;
	}
	public void show(String card) {
		c.show(getContentPane(),  card);
	}
	public EventList getEventlist() {
		return eventList;
	}
	public AddEventGUI getAddEventGUI() {
		return addEventGUI;
	}
	public AddUser getAddUser() {
		return addUser;
	}
	public AddNote getAddNote() {

		return addNote;
	}
	public Calendar getCalendar(){
		return calendar;
	}
}
