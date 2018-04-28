package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import javaC.User;

/**
 * Servlet Filter implementation class AutoLoginFilter
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		HttpServletRequest request=(HttpServletRequest) req;
		Cookie[] cookies=request.getCookies();
		String autologin =null;
		for(int i=0;cookies !=null&& i<cookies.length;i++)
		{
			if("autologin".equals(cookies[i].getName()))
			{
				autologin =cookies[i].getValue();
				break;
				
			}
		}
		if(autologin!=null)
		{
			String[] parts=autologin.split("-");
			String username=parts[0];
			String password=parts[1];
			if("itcast".equals(username)&&("123456").equals(password))
			{
				User user =new User();
				user.setUsername(username);
				user.setPassword(password);
				request.getSession().setAttribute("user", user);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
