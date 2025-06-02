package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

/**
 * LoginAction est l'action qui gère la connexion des utilisateurs.
 * Elle implémente l'interface Action.
 */
public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération des paramètres de la requête
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérification basique : admin/admin
        if("admin".equals(username) && "admin".equals(password)) {

            // Création d'un nouvel utilisateur et stockage dans la session
            UserBean user = new UserBean();
            user.setUsername(username);

            // Initialisation des attributs de l'utilisateur
            user.setAttribut1("default1");
            user.setAttribut2("default2");

            // Stockage de l'utilisateur dans la session
            request.getSession().setAttribute("user", user);

            // Redirection vers la page1.jsp avec les inputs après une connexion réussie
            return "page1.jsp";
        } else {
            // Si les identifiants sont incorrects, on redirige vers la page de connexion avec un message d'erreur
            return "erreur.jsp";
        }
    }
}
