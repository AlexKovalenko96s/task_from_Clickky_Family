<%@page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Statement"%> 
<%@	page import="java.sql.ResultSet"%>	
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
    java.sql.Connection conn=null;
    String Driver="com.mysql.jdbc.Driver";
    Statement stmt = null;
    Class.forName(Driver);
    
    conn = DriverManager.getConnection("jdbc:mysql://localhost/clickky_family", "root", "root");
	Integer hitsCount = (Integer)application.getAttribute("hitCounter");
		
    if(hitsCount ==null || hitsCount == 0 ){
        hitsCount = 1;
//      java.sql.PreparedStatement myStmt = conn.prepareStatement("insert into second (count) values (?)");
		java.sql.PreparedStatement myStmt = conn.prepareStatement("UPDATE second SET count=? WHERE id=?");
        myStmt.setInt(1, hitsCount);
        myStmt.setInt(2, 1);
        myStmt.executeUpdate();    
    }else{
    	 hitsCount += 1;
         java.sql.PreparedStatement myStmt = conn.prepareStatement("UPDATE second SET count=? WHERE id=?");
         myStmt.setInt(1, hitsCount);
         myStmt.setInt(2, 1);
         myStmt.executeUpdate();
    }
    application.setAttribute("hitCounter", hitsCount);
    

	%>

	<img src="${pageContext.request.contextPath}/resources/pic.jpg"
     alt="banner"
     width="1000"
     height="665"
  	/>
</body>
</html>