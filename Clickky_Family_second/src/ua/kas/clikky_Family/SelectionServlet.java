package ua.kas.clikky_Family;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SelectionServlet")
public class SelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelectionServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getServletContext().getRequestDispatcher("/Selection.jsp").forward(request,response);
//		if (request.getParameter("button1") != null) {
//			getServletContext().getRequestDispatcher("/jspView.jsp").forward(request, response);
//        }
//		if (request.getParameter("button2") != null) {
//			getServletContext().getRequestDispatcher("/jspView.jsp").forward(request, response);;
//        }
//		if (request.getParameter("button3") != null) {
//			getServletContext().getRequestDispatcher("/jspView.jsp").forward(request, response);
//        }
//  
//		 request.getRequestDispatcher("/WEB-INF/Selection.jsp").forward(request, response);
	}
}