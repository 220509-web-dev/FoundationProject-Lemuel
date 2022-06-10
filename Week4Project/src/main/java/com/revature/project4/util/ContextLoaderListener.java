package com.revature.project4.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project4.daos.NetflixAccDaoPostgres;
import com.revature.project4.filters.CustomFilter;
import com.revature.project4.services.AuthService;
import com.revature.project4.servlets.AuthServlet;
import com.revature.project4.servlets.NetflixAccServlet;


import javax.servlet.*;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        NetflixAccDaoPostgres accDAO = new NetflixAccDaoPostgres();
        AuthService authService = new AuthService(accDAO);
        NetflixAccServlet netflixAccServlet = new NetflixAccServlet(mapper, accDAO, authService);


        AuthServlet authServlet = new AuthServlet(authService, mapper);
        ServletContext context = sce.getServletContext();

//        CustomFilter customFilter = new CustomFilter();
//        context.addFilter("CustomFilter", customFilter).addMappingForUrlPatterns(EnumSet.of(DispatcherType.), true, "/*");

        ServletRegistration.Dynamic registeredServlet = context.addServlet("NetflixAccServlet", netflixAccServlet);
        registeredServlet.setLoadOnStartup(3);
        registeredServlet.setInitParameter("netflixAcc-servlet-key", "netflixAcc-servlet-value");
        registeredServlet.addMapping("/netflixAcc/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}