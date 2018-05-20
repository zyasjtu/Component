package org.cora.filter;

import org.cora.model.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Colin
 */
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String url = httpServletRequest.getRequestURI();

        if (user != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else if (url.contains("checkUser") || url.contains("addUser") || url.contains("index")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.sendRedirect("index.jsp");
        }
    }
}
