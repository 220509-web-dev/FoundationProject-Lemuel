package com.revature.project4.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project4.dtos.Credentials;
import com.revature.project4.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final AuthService authService;
    public AuthServlet( AuthService authService,ObjectMapper mapper) {
        this.mapper = mapper;
        this.authService = authService;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Use the ObjectMapper to convert the JSON request payload to a Java object
        Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
        //try {
            // Use the AuthService to validate that the provided credentials meet business rules
                if (creds.getUsername() != null && creds.getPassword() != null)
                {
                    authService.login(creds.getUsername(), creds.getPassword());
                    String z = String.valueOf(authService.login(creds.getUsername(), creds.getPassword()));
                    HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                    session.setAttribute("auth-user", z); // this attribute is visible on any requests with this session attached
                }

        {

        }
            // after validation, if no exception thrown -send back response (establish HttpSession)

       /* } catch (InvalidCredentialsException e) {
            resp.setStatus(400);
            resp.setContentType("application/json");
            HashMap<String, Object> errorMessage = new HashMap<>();
            errorMessage.put("code", 400);
            errorMessage.put("message", "No user found with provided credentials");
            errorMessage.put("timestamp", LocalDateTime.now().toString());

            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
        }
        */

    }

}

/*
        try {
                // Use the AuthService to validate that the provided credentials meet business rules
                // List<NetflixAcc> users = new NetflixAccDAO().getAccs();
                String providedUsername = (String) creds.getUsername();
                String providedPassword = (String) creds.getPassword();
                for (NetflixAcc user : users) {
                if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
                System.out.println("[LOG] - found user!");

                // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
                HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                session.setAttribute("auth-user", user); // this attribute is visible on any requests with this session attached

                resp.setStatus(204); // NO CONTENT (success but nothing to return)
                return; // return here otherwise we continue and bad things might happen
                }
                }
                // after validation, if no exception thrown -send back response (establish HttpSession)

                } catch (InvalidCredentialsException e) {
                resp.setStatus(400);
                resp.setContentType("application/json");
                HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("code", 400);
        errorMessage.put("message", "No user found with provided credentials");
        errorMessage.put("timestamp", LocalDateTime.now().toString());

        resp.getWriter().write(mapper.writeValueAsString(errorMessage));
        }

*/


