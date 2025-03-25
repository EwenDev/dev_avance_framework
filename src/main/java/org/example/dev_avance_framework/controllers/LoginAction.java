package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

@WebServlet(name="LoginAction", urlPatterns={"/LoginAction"})
public class LoginAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Pour l'exemple, la validation est basique (ex: admin/admin)
        if("admin".equals(username) && "admin".equals(password)){
            // Création et initialisation du bean utilisateur
            UserBean user = new UserBean();
            user.setUsername(username);
            user.setAttribut1("default1");
            user.setAttribut2("default2");

            // Stockage du bean dans la session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirection vers la page d'accueil
            RequestDispatcher dispatcher = request.getRequestDispatcher("page1.jsp");
            dispatcher.forward(request, response);
        } else {
            // Redirection vers la page d'erreur
            RequestDispatcher dispatcher = request.getRequestDispatcher("erreur.jsp");
            dispatcher.forward(request, response);
        }
    }
}
