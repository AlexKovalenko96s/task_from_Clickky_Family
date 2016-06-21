package ua.kas.clickky_family;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

	@FXML Label label_name;
	ArrayList<String> al_company_name = new ArrayList<String>();
	ArrayList<String> al_phone = new ArrayList<String>();
	ArrayList<String> al_table = new ArrayList<String>();
	
	public void press(ActionEvent e) throws SQLException{
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clickky_family", "root", "root");
		Statement rstat = conn.createStatement();
		ResultSet result = rstat.executeQuery("SELECT DISTINCT company FROM first");
		
		while(result.next()){
			al_company_name.add(result.getString("company"));
		}
		
		java.sql.PreparedStatement myStmt;
		
		for(int i=0; i<al_company_name.size(); i++){
			myStmt = conn.prepareStatement("SELECT type FROM first where company = ?");
			myStmt.setString(1, al_company_name.get(i));
			ResultSet result2 = myStmt.executeQuery();
			while(result2.next()){
				if(result2.getString("type").equals("phone")){
					al_phone.add(result2.getString("type"));
				}
				if(result2.getString("type").equals("table")){
					al_table.add(result2.getString("type"));
				}
			}
			if((al_phone.size()>al_table.size()) && (al_table.size()>3) && ((al_table.size()+al_phone.size())<10)){
				label_name.setText(al_company_name.get(i));
			}
			else{
				System.out.println(al_company_name.get(i)+" - unsuitable!");
			}
			al_phone.clear();
			al_table.clear();
		}	
	}
}
