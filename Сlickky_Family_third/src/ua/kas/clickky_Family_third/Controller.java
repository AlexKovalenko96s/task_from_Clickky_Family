package ua.kas.clickky_Family_third;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javenue.csv.Csv;

public class Controller implements Initializable {

	static String line = "", ip, url_from, url_where, os, browser, line2="";
	static DateFormat format_date = new SimpleDateFormat("dd.MM.yyyy");
	static DateFormat format_time = new SimpleDateFormat("HH:mm");
	private static File file_first = new File("first");
	private static File file_second = new File("second");
	@FXML TableView<Employee> table;
	@FXML TableColumn<Employee, String> t_ip;
	@FXML TableColumn<Employee, String> t_os;
	@FXML TableColumn<Employee, String> t_browser;
	@FXML TableColumn<Employee, String> t_firstUrl;
	@FXML TableColumn<Employee, String> t_lastUrl;
	@FXML TableColumn<Employee, Integer> t_uniqueUrl;
	@FXML TableColumn<Employee, String> t_time;
	String sub_time;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		table.setEditable(true);
		FileReader fr;
		java.sql.PreparedStatement myStmt;
		
		t_ip.setCellValueFactory(new PropertyValueFactory<Employee, String>("ip"));
		t_os.setCellValueFactory(new PropertyValueFactory<Employee, String>("os"));
		t_browser.setCellValueFactory(new PropertyValueFactory<Employee, String>("browser"));
		t_firstUrl.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstUrl"));
		t_lastUrl.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastUrl"));
		t_uniqueUrl.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("uniqueUrl"));
		t_time.setCellValueFactory(new PropertyValueFactory<Employee, String>("time"));
		
//		try {
//			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clickky_family", "root", "root");
//			try {
//				fr = new FileReader(file_first);
//				BufferedReader br = new BufferedReader(fr);
//				while ((line = br.readLine()) != null) {
//					
//					Date date = format_date.parse(line.substring(0, line.indexOf("|")));
//					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//					line = line.substring(line.indexOf("|") + 1);
//					Date time = format_time.parse(line.substring(0, line.indexOf("|")));
//					java.sql.Time sqlTime = new java.sql.Time(time.getTime());
//					line = line.substring(line.indexOf("|") + 1);
//					ip = line.substring(0, line.indexOf("|"));
//					line = line.substring(line.indexOf("|") + 1);
//					url_from = line.substring(0, line.indexOf("|"));
//					line = line.substring(line.indexOf("|") + 1);
//					url_where = line;
//
//					myStmt = myConn.prepareStatement("insert into third_history (date, time, ip, url_from, url_where) values (?,?,?,?,?)");
//					myStmt.setDate(1, sqlDate);
//					myStmt.setTime(2, sqlTime);
//					myStmt.setString(3, ip);
//					myStmt.setString(4, url_from);
//					myStmt.setString(5, url_where);
//					
//					myStmt.executeUpdate();
//				}
//				fr.close();
//				br.close();
//			} catch (FileNotFoundException e) {
//			} catch (IOException e) {
//			} catch (ParseException e) {}
//			
//			try {
//				fr = new FileReader(file_second);
//				BufferedReader br = new BufferedReader(fr);
//				while ((line = br.readLine()) != null) {
//					
//					ip = line.substring(0, line.indexOf("|"));
//					line = line.substring(line.indexOf("|") + 1);
//					browser = line.substring(0, line.indexOf("|"));
//					line = line.substring(line.indexOf("|") + 1);
//					os = line;
//				
//					myStmt = myConn.prepareStatement("insert into third_users (ip, os, browser) values (?,?,?)");
//					myStmt.setString(1, ip);
//					myStmt.setString(3, browser);
//					myStmt.setString(2, os);
//					
//					myStmt.executeUpdate();
//				}
//				fr.close();
//				br.close();
//			} catch (FileNotFoundException e) {
//			} catch (IOException e) {}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		ObservableList<Employee> data = FXCollections.observableArrayList();
		
		try {
			Connection myConn;
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clickky_family", "root", "root");
			Statement rstat = myConn.createStatement();
			ResultSet result = rstat.executeQuery("select third_users.ip, third_users.os, third_users.browser,"
					+ "@First:=(select third_history.url_from FROM third_history, third_users where "
					+ "third_history.ip=third_users.ip ORDER BY third_history.time asc LIMIT 1), "
					+ "@Last:=(select third_history.url_where FROM third_history, third_users where "
					+ "third_history.ip=third_users.ip ORDER BY third_history.time desc LIMIT 1), "
					+ "@Unique:= COUNT(distinct third_history.url_from), "
					+ "@Time:=(select max(third_history.time) - min(third_history.time) "
					+ "FROM third_history, third_users where third_history.ip=third_users.ip)"
					+ "FROM third_history, third_users where third_history.ip=third_users.ip "
					+ "group by third_users.ip, third_users.os, third_users.browser");
			
			while(result.next()){
//				sub_time=result.getString("@Time:=(select max(third_history.time) - min(third_history.time) FROM third_history, third_users where third_history.ip=third_users.ip)");
//				String time= "00:00:00";
//				int l=0;
//				for(int i= sub_time.length(); i>0; i--){
//					int k=8-i;
//					time = time.replace(time.charAt(k), sub_time.charAt(l));
//				//	System.out.println(k);
//				//	System.out.println(sub_time.charAt(l));	
//					System.out.println(time.charAt(k));
//					System.out.println(time);
//					l++;
//				}
//						
				data.add(new Employee(result.getString("third_users.ip"), result.getString("third_users.os"), result.getString("third_users.browser"), result.getString("@First:=(select third_history.url_from FROM third_history, "
						+ "third_users where third_history.ip=third_users.ip ORDER BY third_history.time asc LIMIT 1)"), result.getString("@Last:=(select third_history.url_where FROM third_history, "
						+ "third_users where third_history.ip=third_users.ip ORDER BY third_history.time desc LIMIT 1)"), result.getInt("@Unique:= COUNT(distinct third_history.url_from)"),
						result.getString("@Time:=(select max(third_history.time) - min(third_history.time) FROM third_history, third_users where third_history.ip=third_users.ip)")));

//				System.out.println(result.getString("third_users.ip")+"  "+result.getString("third_users.os")+"  "+result.getString("third_users.browser"));
//				System.out.println(result.getString("@First:=(select third_history.url_from FROM third_history, "
//						+ "third_users where third_history.ip=third_users.ip ORDER BY third_history.time asc LIMIT 1)"));
//				System.out.println(result.getString("@Last:=(select third_history.url_where FROM third_history, "
//						+ "third_users where third_history.ip=third_users.ip ORDER BY third_history.time desc LIMIT 1)"));
//				System.out.println(result.getInt("@Unique:= COUNT(distinct third_history.url_from)"));
//				System.out.println(result.getString("@Time:=(select max(third_history.time) - min(third_history.time) FROM third_history, third_users where third_history.ip=third_users.ip)"));
			}
			table.setItems(data);
		}catch(SQLException e){e.printStackTrace();} 
	}
	
	public void addCSV(ActionEvent e){
		Csv.Writer writer = new Csv.Writer("csv").delimiter('|');
		  writer.comment("csv")
		      .value(t_ip.getText()).value(t_os.getText()).value(t_browser.getText()).value(t_firstUrl.getText()).value(t_lastUrl.getText()).value(t_uniqueUrl.getText()).newLine()
		      .value(t_ip.getCellData(0)).value(t_os.getCellData(0)).value(t_browser.getCellData(0)).value(t_firstUrl.getCellData(0)).value(t_lastUrl.getCellData(0)).value(Integer.toString(t_uniqueUrl.getCellData(0))).newLine()
		      .value(t_ip.getCellData(1)).value(t_os.getCellData(1)).value(t_browser.getCellData(1)).value(t_firstUrl.getCellData(1)).value(t_lastUrl.getCellData(1)).value(Integer.toString(t_uniqueUrl.getCellData(1))).close();
	}
	
	public void seeCSV(ActionEvent e) throws FileNotFoundException{
//		 Csv.Reader reader = new Csv.Reader(new FileReader("csv"))
//			      .delimiter('|').ignoreComments(true);
//			  System.out.println(reader.readLine());
		FileReader fr;
		try {
			fr = new FileReader("csv");
			BufferedReader br = new BufferedReader(fr);		
			while((line2 = br.readLine()) != null){
				System.out.println(line2);
			}
			fr.close();
			br.close();
		}catch(Exception ex){}
	}
}