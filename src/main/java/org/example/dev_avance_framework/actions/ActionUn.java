package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

/**
 * ActionUn est une action qui met à jour les attributs d'un utilisateur
 */
public class ActionUn implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la session et de l'utilisateur
        HttpSession session = request.getSession();

        // Mise à jour des attributs de l'utilisateur
        UserBean user = (UserBean) session.getAttribute("user");

        // Vérification si l'utilisateur existe dans la session
        if(user != null) {
            // Mise à jour des attributs de l'utilisateur avec les paramètres de la requête
            user.setAttribut1(request.getParameter("attribut1"));
            user.setAttribut2(request.getParameter("attribut2"));
        }

        // Redirection vers la page1.jsp
        return "page1.jsp";
    }
}
