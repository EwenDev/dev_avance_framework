package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ActionDebut est l'action initiale qui redirige vers la page de connexion.
 * Elle implémente l'interface Action.
 */
public class ActionDebut implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "login.jsp";
    }
}
