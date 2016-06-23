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
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
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
	ArrayList<String> al_date = new ArrayList<String>();
//	Map<String, Integer> map = new HashMap<String, Integer>();
	int k = 0;
	int more = 0;
	int less = 100;
	ArrayList<String> al_more = new ArrayList<String>();
	ArrayList<String> al_less = new ArrayList<String>();
	
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
	
	for(int i=0; i<al_date.size(); i++){
		myStmt = conn.prepareStatement("select time from chart where date = ?");
		myStmt.setString(1, al_date.get(i));
		ResultSet result2 = myStmt.executeQuery();
		while(result2.next()){
			k++;
		}
		if(k==more){
			al_more.add(al_date.get(i));
		}
		if(k>more){
			more=k;
			al_more.clear();
			al_more.add(al_date.get(i));
		}
		if(k==less){
			al_less.add(al_date.get(i));
		}
		if(k<less){
			less=k;
			al_less.clear();
			al_less.add(al_date.get(i));
		}
		k=0;
	}
	out.print(more+" users - "+ al_more +"________"+less+" users - "+al_less);
%>
</body>
</html>