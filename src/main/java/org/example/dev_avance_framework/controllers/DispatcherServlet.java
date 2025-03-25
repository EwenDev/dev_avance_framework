package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.example.dev_avance_framework.actions.Action;
import org.example.dev_avance_framework.actions.ActionDebut;
import org.example.dev_avance_framework.actions.LoginAction;
import org.example.dev_avance_framework.actions.ActionUn;
import org.example.dev_avance_framework.actions.LogoutAction;

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
        String actionPath = request.getServletPath();
        Action action = null;
        String view = "";

        // Instanciation de l'action en fonction du chemin
        switch(actionPath) {
            case "/ActionDebut.do":
                action = new ActionDebut();
                break;
            case "/LoginAction.do":
                action = new LoginAction();
                break;
            case "/ActionUn.do":
                action = new ActionUn();
                break;
            case "/LogoutAction.do":
                action = new LogoutAction();
                break;
            default:
                view = "index.jsp";
        }

        // Si une action a été trouvée, exécutez-la pour récupérer l'URL de la vue
        if(action != null) {
            view = action.execute(request, response);
        }

        // Dispatch vers la vue retournée par l'action
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
