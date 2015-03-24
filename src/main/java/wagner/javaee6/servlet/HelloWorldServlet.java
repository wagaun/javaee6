package wagner.javaee6.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("servlets/hello")
public class HelloWorldServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Hello world</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Servlet, Hello world!</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}
}
