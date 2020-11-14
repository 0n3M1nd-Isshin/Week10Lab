package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 823280
 */
public class AdminFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        //Executes before the servlet / next filer
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        
        //if user != admin, wont be able to access /admin and is redirected to 
        // /notes if already logged in.
        if (!email.equals("cprg352+admin@gmail.com")) {
            //if email is null, redirect user back to the login page
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("notes");
            return;
        }
        
        
        
        chain.doFilter(request, response); //Chain to the servlet / next filter
        
        //Execute after the servlet / filter
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
      
    }

    @Override
    public void destroy() {
        
    }
}
