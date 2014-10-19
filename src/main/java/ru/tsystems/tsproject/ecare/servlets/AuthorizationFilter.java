package ru.tsystems.tsproject.ecare.servlets;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Selvin
 * on 03.10.2014.
 */
public class AuthorizationFilter implements Filter {
    private FilterConfig config = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Boolean isOn = Boolean.valueOf(req.getParameter("sessionStatus"));
        if(!isOn) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
