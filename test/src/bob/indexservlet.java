package bob;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class indexservlet
 */
@WebServlet("/indexservlet")
public class indexservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		user user=(user)session.getAttribute("user");
		System.out.println("test");
		if(user==null) {
			response.getWriter().print("Äú»¹Ã»ÓÐµÇÂ¼£¬Çë<a href='/chapter03/login.html'>µÇÂ¼</a>");
		}else {
			
			response.getWriter().print("ÄúÒÑµÇÂ¼£¬»¶Ó­Äã"+user.getUsername()+"!");
			response.getWriter().print("<a href='/chapter03/LogoutServlet'>ÍË³ö</a>");
			System.out.println("test");
			Cookie cookie=new Cookie("JSESSIONID", session.getId());
		
			cookie.setMaxAge(60*30);
			System.out.println("test");
			cookie.setPath("/chapter03");
			response.addCookie(cookie);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
