package ua.kas.clickky_family;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

	@FXML
	Label label_name;

	public void press(ActionEvent e) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/clickky_family", "root", "root");
		Statement rstat = conn.createStatement();
		ResultSet result = rstat
				.executeQuery("select first.company, COUNT(first.type)<10 and sum(first.type like '%table%')>=3 "
						+ "and sum(first.type like '%phone%')>sum(first.type like '%table%') from first  group by first.company");
		while (result.next()) {
			if (result
					.getString(
							"COUNT(first.type)<10 and sum(first.type like '%table%')>=3 and sum(first.type like '%phone%')>sum(first.type like '%table%')")
					.equals("1")) {
				label_name.setText(result.getString("company"));
			} else {
				System.out.println(result.getString("company") + " - not correct!");
			}
		}
	}
}
