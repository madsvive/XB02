import java.sql.SQLException;
import java.sql.Savepoint;

import model.QOTD.QOTDModel;
import model.calendar.Event;
import model.calendar.GetCalendarData;
import model.note.Note;
import JsonClasses.AuthUser;
import JsonClasses.CalendarInfo;
import JsonClasses.CreateCalendar;
import JsonClasses.DeleteCalendar;

import com.google.gson.*;

import databaseMethods.SwitchMethods;

public class GiantSwitch {
	
	
	
	public String GiantSwitchMethod(String jsonString) throws SQLException {

		//Events eventsKlasse = new Events(0, 0, 0, jsonString, jsonString, jsonString, jsonString, jsonString);

		Note noteKlasse = new Note();
		//ForecastModel forecastKlasse = new ForecastModel();
		QOTDModel QOTDKlasse = new QOTDModel();
		SwitchMethods SW = new SwitchMethods();
		
		Gson gson = new GsonBuilder().create();
		String answer = "";	
		//Creates a switch which determines which method should be used. Methods will be applied later on
		switch (Determine(jsonString)) {
		//If the Json String contains one of the keywords below, run the relevant method.

		/************
		 ** COURSES **
		 ************/
		//Import calendar henter cbskalendar til det gældene username. 
		case "importCalendar":
			try {
				GetCalendarData importCalendar = new GetCalendarData();
			System.out.println("Recieved calendar from cbscalendar");
			} catch (Exception e1){
			System.out.println("Can't importcalender");
			System.out.println(e1);
			}
			break;	

		/**********
		 ** LOGIN **
		 **********/
		case "logIn":
			AuthUser AU = (AuthUser)gson.fromJson(jsonString, AuthUser.class);
			System.out.println("Recieved logIn");
			try {
				answer = SW.authenticate(AU.getAuthUserEmail(), AU.getAuthUserPassword(), AU.getAuthUserIsAdmin());
			} catch (Exception e) {
				System.out.println("Login can't get values to login.. see Giant switch case logins");
				e.printStackTrace();
			}
			break;

		case "logOut":
			System.out.println("Recieved logOut");
			break;

		/*************
		 ** CALENDAR **
		 *************/
		case "createCalendar":
			CreateCalendar CC = (CreateCalendar)gson.fromJson(jsonString, CreateCalendar.class);
			System.out.println(CC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.createNewCalendar(CC.getUserName(), CC.getCalendarName(), CC.getPublicOrPrivate());
			break;
		
		case "deleteCalendar":
			DeleteCalendar DC = (DeleteCalendar)gson.fromJson(jsonString, DeleteCalendar.class);
			System.out.println(DC.getCalendarName()+ "Den har lagt det nye ind i klassen");
			answer = SW.deleteCalendar(DC.getUserName(), DC.getCalendarName()); 
			break;
		
		case "saveImportedCalendar":
			SaveImportedCalendar SIC = (SaveImportedCalendar)gson.fromJson(jsonString, SaveimportedCalendar.class);
			System.out.println(SIC.getCalendarName() + "Den nye kalender er nu succesfuldt blevet gemt");
			answer = SW.SaveImportedCalendar(SIC.getUserName(), SIC.getCalendarName());				
			break;
			//Klasse ikke oprettet.
			
			
		case "getCalendar":
			GetCalendar GC = (GetCalendar)gson.fromJson(jsonString, GetCalendar.class);
			System.out.println(GC.getCalendarName() + "Den nye kalender er nu hentet");
			answer = SW.GetCalendar(GC.getUserName(), GC.getCalendarName());
			//			System.out.println("Recieved getCalendar");
			break;
			//Klasse ikke oprettet.
			

		case "getEvents":
			GetEvents GE = (GetEvents)gson.fromJson(jsonString, GetEvent.class);
			System.out.println(GE.getCalendarName() + "Det nye event er nu hentet");
			answer = SW.GetEvent(GC.getUserName(), GC.getCalendarName());
//			System.out.println("Recieved getEvents");
			break;
			//Klasse ikke oprettet.
			

		case "createEvent":
			System.out.println("Recieved saveEvent");
			break;

		case "getEventInfo":
			System.out.println("Recieved getEventInfo");
			break;
			
		case "deleteEvent":
			System.out.println("Recieved deleteEvent");
		
		case "saveNote":
			System.out.println("Recieved saveNote");
			break;

		case "getNote":
			System.out.println("Recieved getNote");
			break;
			
		case "deleteNote":
			System.out.println("Recieved deleteNote");
			break;

		/**********
		 ** QUOTE **
		 **********/
		case "getQuote":

		answer = QOTDKlasse.getQuote();
			System.out.println(answer);
			
			break;

		/************
		 ** WEATHER **
		 ************/

		case "getClientForecast":
			System.out.println("Recieved getClientForecast");
			break;
		
		default:
			System.out.println("Error");
			break;
		}
		return answer;
		
	}

	//Creates a loooong else if statement, which checks the JSon string which keyword it contains, and returns the following 
	//keyword if
	public String Determine(String ID) {

		if (ID.contains("getEvents")) {
			return "getEvents";
		} else if (ID.contains("getEventInfo")) {
			return "getEventInfo";
		} else if (ID.contains("saveNote")) {
			return "saveNote";
		} else if (ID.contains("getNote")) {
			return "getNote";
		} else if (ID.contains("deleteNote")){
			return "deleteNote";
		}else if  (ID.contains("deleteCalendar")){
			return "deleteCalendar";
		} else if (ID.contains("getClientForecast")) {
			return "getClientForecast";
		} else if (ID.contains("saveImportedCalendar")) {
			return "saveImportedCalendar";
		}else if (ID.contains("importCourse")) {
			return "importCourse";
		} else if (ID.contains("exportCourse")) {
			return "exportCourse";
		} else if (ID.contains("getQuote")) {
			return "getQuote";
		} else if (ID.contains("logIn")) {
			return "logIn";
		} else if (ID.contains("logOut")) {
			return "logOut";
		} else if (ID.contains("getCalendar")) {
			return "getCalendar";
		} else if (ID.contains("createEvent")) {
			return "createEvent";
		} else if (ID.contains("deleteEvent")) {
			return "deleteEvent"; 
		} else if (ID.contains("createCalendar")) {
			return "createCalendar";
		}

		else
			return "error";
	}
	

}
