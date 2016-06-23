<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<String> al_date = new ArrayList<String>();
	ArrayList<String> al_time = new ArrayList<String>();
	ArrayList<String> t0_3 = new ArrayList<String>();
	ArrayList<String> t4_6 = new ArrayList<String>();
	ArrayList<String> t7_9 = new ArrayList<String>();
	ArrayList<String> t10_12 = new ArrayList<String>();
	ArrayList<String> t13_15 = new ArrayList<String>();
	ArrayList<String> t16_18 = new ArrayList<String>();
	ArrayList<String> t19_21 = new ArrayList<String>();
	ArrayList<String> t22_24 = new ArrayList<String>();
	
	java.sql.Connection conn = null;
	String Driver = "com.mysql.jdbc.Driver";
	Statement stmt = null;
	Class.forName(Driver);
	java.sql.PreparedStatement myStmt;
	conn = DriverManager.getConnection("jdbc:mysql://localhost/clickky_family", "root", "root");

	myStmt = conn.prepareStatement("SELECT DISTINCT date FROM chart");
	ResultSet myRs = myStmt.executeQuery();
	while (myRs.next()) {
		al_date.add(myRs.getString("date"));
	}
	
	al_date.sort(null);
	
	myStmt = conn.prepareStatement("select time from chart where date = ?");
	myStmt.setString(1, al_date.get(al_date.size()-1));
	ResultSet result2 = myStmt.executeQuery();
	while(result2.next()){
		al_time.add(result2.getString("time"));
	}
	
	String s;
	for(int i=0; i<al_time.size(); i++){
		s = al_time.get(i);
		s = s.substring(0,2);
		if((Integer.parseInt(s)<=3) && (Integer.parseInt(s)>=0)){
			t0_3.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=6) && (Integer.parseInt(s)>=4)){
			t4_6.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=9) && (Integer.parseInt(s)>=7)){
			t7_9.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=12) && (Integer.parseInt(s)>=10)){
			t10_12.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=15) && (Integer.parseInt(s)>=13)){
			t13_15.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=18) && (Integer.parseInt(s)>=16)){
			t16_18.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=21) && (Integer.parseInt(s)>=19)){
			t19_21.add(al_time.get(i));
		}
		if((Integer.parseInt(s)<=24) && (Integer.parseInt(s)>=22)){
			t22_24.add(al_time.get(i));
		}
	}
	
	DefaultPieDataset data = new DefaultPieDataset();

	if(t0_3.size()!=0){
		data.setValue(Integer.toString(t0_3.size()), 00.03);
	}
	if(t4_6.size()!=0){
		data.setValue(Integer.toString(t4_6.size()), 04.06);
	}
	if(t7_9.size()!=0){
		data.setValue(Integer.toString(t7_9.size()), 07.09);
	}
	if(t10_12.size()!=0){
		data.setValue(Integer.toString(t10_12.size()), 10.12);
	}
	if(t13_15.size()!=0){
		data.setValue(Integer.toString(t13_15.size()), 13.15);
	}
	if(t16_18.size()!=0){
		data.setValue(Integer.toString(t16_18.size()), 16.18);
	}
	if(t19_21.size()!=0){
		data.setValue(Integer.toString(t19_21.size()), 19.21);
	}
	if(t22_24.size()!=0){
		data.setValue(Integer.toString(t22_24.size()), 22.24);
	}
	
	JFreeChart grafico = ChartFactory.createPieChart("Day", data, true, true, true);

	response.setContentType("image/JPEG");
	OutputStream sa = response.getOutputStream();
	ChartUtilities.writeChartAsJPEG(sa, grafico, 500, 500);
%>
</body>
</html>