<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.jfree.chart.ChartFactory" %>
<%@ page import="org.jfree.chart.ChartUtilities" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation"%>
<%@ page import="org.jfree.data.*" %>
<%@ page import="org.jfree.data.jdbc.JDBCCategoryDataset"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clickky Family</title>
</head>
<body>

<%
	
	DefaultCategoryDataset data = new DefaultCategoryDataset();

	ArrayList<String> al_date = new ArrayList<String>();
	ArrayList<String> al_time = new ArrayList<String>();
	
	java.sql.Connection conn=null;
	String Driver="com.mysql.jdbc.Driver";
	Statement stmt = null;
	Class.forName(Driver);
	java.sql.PreparedStatement myStmt;
	conn = DriverManager.getConnection("jdbc:mysql://localhost/clickky_family", "root", "root");
	
	myStmt = conn.prepareStatement("SELECT DISTINCT date FROM chart");
	ResultSet myRs = myStmt.executeQuery();
	while(myRs.next()){
		al_date.add(myRs.getString("date"));
	}
	
	al_date.sort(null);
	for(int i=al_date.size()-1; i>al_date.size()-8; i--){
		out.println(i);
		myStmt = conn.prepareStatement("select time from chart where date = ?");
		myStmt.setString(1, al_date.get(i));
		ResultSet result2 = myStmt.executeQuery();
		while(result2.next()){
			al_time.add(result2.getString("time"));
		}
		data.setValue(al_time.size(),"users", al_date.get(i));
		al_time.clear();
	}

	JFreeChart grafico = ChartFactory.createBarChart("Chart", "Date", "Number of users", data,PlotOrientation.VERTICAL,true,  true, true );
	response.setContentType("image/JPEG");
	OutputStream sa = response.getOutputStream();

	ChartUtilities.writeChartAsJPEG(sa ,grafico, 800, 500);

%>
</body>
</html>