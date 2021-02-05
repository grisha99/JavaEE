package ru.grishchenko;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    private ServletConfig config;

    public void init(ServletConfig servletConfig) throws ServletException {
        logger.info("FirstServlet Init");
        this.config = config;
    }

    public ServletConfig getServletConfig() {
        return this.config;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to FirstServlet");
        servletResponse.getWriter().println("<h1>Hello world!!!</h1>");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
