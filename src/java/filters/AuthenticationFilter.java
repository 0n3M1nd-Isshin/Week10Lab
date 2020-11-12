/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/*"}, servletNames = {"NoteServlet"})

public class AuthenticationFilter implements Filter {
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        //this code will execute before the servlet
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        
        if (email == null) {
            //if email is null, redirect user back to the login page
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect("login");
            return;
        }
        
        
        chain.doFilter(request, response); //forward on to the servlet, or next filter
        
        
        //this code will excute ater the servlet

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void destroy() {
        
    }
}