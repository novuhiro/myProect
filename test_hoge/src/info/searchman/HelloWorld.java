package info.searchman;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 	private final String loginId = "id123";
	    private final String password = "password123";


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Document</title>");
		out.println("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css' integrity='sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh' crossorigin='anonymous'>");
		out.println("<script src='https://code.jquery.com/jquery-3.4.1.slim.min.js' integrity='sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n' crossorigin='anonymous'></script>");
		out.println("<script src='https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js' integrity='sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo' crossorigin='anonymous'></script>");
		out.println("<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js' integrity='sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6' crossorigin='anonymous'></script>");
		out.println("</head>");
		out.println("<body class='text-center'>");
		out.println("<form class='form-signin'>");
		out.println("<img class='mb-4' src='/docs/4.4/assets/brand/bootstrap-solid.svg' alt='' width='72' height='72'>");
		out.println("<h1 class='h3 mb-3 font-weight-normal'>Please sign in</h1>");
		out.println("<label for='inputEmail' class='sr-only'>Email address</label>");
		out.println("<input type='email' id='inputEmail' class='form-control' placeholder='Email address' required='' autofocus=''>");
		out.println("<label for='inputPassword' class='sr-only'>Password</label>");
		out.println("<input type='password' id='inputPassword' class='form-control' placeholder='Password' required=''>");
		out.println("<div class='checkbox mb-3'>");
		out.println("<label>");
		out.println("<input type='checkbox' value='remember-me'> Remember me");
		out.println("</label>");
		out.println("</div>");
		out.println("<button class='btn btn-lg btn-primary btn-block' type='submit'>Sign in</button>");
		out.println("<p class='mt-5 mb-3 text-muted'>© 2017-2019</p>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		
		
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        String sentId = request.getParameter("login-id");
	        String sentPw = request.getParameter("password");

	        if (sentId.equals(loginId) && sentPw.equals(password)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("loginUser", true);
	            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/home.jsp");
	            rd.forward(request, response);
	        } else {
	            request.setAttribute("loginErrorMsg", "ログイン情報が不正です。");
	            request.setAttribute("errorFlg", true);
	            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
	            rd.forward(request, response);
	        }
	    }

}