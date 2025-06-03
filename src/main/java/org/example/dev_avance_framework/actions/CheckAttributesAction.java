package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

/**
 * CheckAttributesAction vérifie les attributs de l'utilisateur et les met à jour dans la session.
 * Elle implémente l'interface Action.
 */
public class CheckAttributesAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération des attributs depuis la requête
        String attr1 = request.getParameter("attribut1");
        String attr2 = request.getParameter("attribut2");

        // Vérification si les attributs sont vides ou null
        if (attr1 == null || attr1.trim().isEmpty() || attr2 == null || attr2.trim().isEmpty()) {
            // Si l'un des attributs est vide, on redirige vers page1.jsp avec un message d'erreur
            request.setAttribute("errorMessage", "Les deux attributs doivent être remplis !");
            return "page1.jsp";
        }

        // Récupération de la session et de l'utilisateur
        HttpSession session = request.getSession(false);

        // Si la session existe, on met à jour les attributs de l'utilisateur
        if (session != null) {

            // Récupération de l'utilisateur depuis la session
            UserBean user = (UserBean) session.getAttribute("user");
            // Vérification si l'utilisateur existe dans la session puis mise à jour de ses attributs
            if (user != null) {
                user.setAttribut1(attr1);
                user.setAttribut2(attr2);
            }
        }

        // Redirection vers la page "on commence à travailler"
        return "working.jsp";
    }
}
