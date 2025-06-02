package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

/**
 * LogoutAction est une action qui gère la déconnexion d'un utilisateur.
 * Elle implémente l'interface Action.
 */
public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la session et de l'utilisateur
        HttpSession session = request.getSession(false);

        // Si la session existe, on récupère le nom d'utilisateur et on invalide la session
        String username = "";

        // Vérification si la session n'est pas nulle
        if(session != null) {

            // Récupération de l'utilisateur depuis la session
            UserBean user = (UserBean) session.getAttribute("user");

            // Si l'utilisateur existe, on récupère son nom d'utilisateur
            if(user != null) {
                username = user.getUsername();
            }

            // Invalidation de la session pour déconnecter l'utilisateur
            session.invalidate();
        }

        // Redirection vers la page de déconnexion avec le nom d'utilisateur
        request.setAttribute("username", username);

        // Retourne le nom de la page JSP de déconnexion
        return "goodbye.jsp";
    }
}
