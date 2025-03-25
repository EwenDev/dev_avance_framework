package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

@WebServlet(name = "DispatcherServlet", urlPatterns = {"*.do"})
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        String view = "";

        switch(action) {
            case "/ActionDebut.do":
                // Redirige vers la page de login
                view = "login.jsp";
                break;

            case "/LoginAction.do":
                // Récupération des paramètres du formulaire de login
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                // Vérification basique des identifiants (exemple : admin/admin)
                if("admin".equals(username) && "admin".equals(password)) {
                    UserBean user = new UserBean();
                    user.setUsername(username);
                    user.setAttribut1("default1");
                    user.setAttribut2("default2");
                    request.getSession().setAttribute("user", user);
                    view = "page1.jsp";
                } else {
                    view = "erreur.jsp";
                }
                break;

            case "/ActionUn.do":
                // Mise à jour des attributs utilisateur
                HttpSession session = request.getSession();
                UserBean user = (UserBean) session.getAttribute("user");
                if(user != null) {
                    user.setAttribut1(request.getParameter("attribut1"));
                    user.setAttribut2(request.getParameter("attribut2"));
                }
                view = "page1.jsp";
                break;

            case "/LogoutAction.do":
                // Déconnexion et affichage de la page goodbye
                session = request.getSession(false);
                String usernameLogout = "";
                if(session != null) {
                    user = (UserBean) session.getAttribute("user");
                    if(user != null) {
                        usernameLogout = user.getUsername();
                    }
                    session.invalidate();
                }
                request.setAttribute("username", usernameLogout);
                view = "goodbye.jsp";
                break;

            default:
                // Action par défaut ou page d'accueil
                view = "index.jsp";
        }
        // Redirection vers la vue correspondante
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
