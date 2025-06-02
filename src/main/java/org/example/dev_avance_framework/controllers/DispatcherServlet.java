package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import org.example.dev_avance_framework.actions.Action;
import org.example.dev_avance_framework.factory.MyFactory;

/**
 * DispatcherServlet est le contrôleur principal qui gère les requêtes HTTP.
 * Il utilise une factory pour instancier dynamiquement les actions basées sur la configuration XML.
 */
public class DispatcherServlet extends HttpServlet {

    // Map pour stocker les associations entre les noms d'actions et les classes d'actions
    private Map<String, Class<? extends Action>> actionMap;

    /**
     * Méthode d'initialisation du servlet.
     * Elle est appelée une seule fois lors du chargement du servlet.
     * Elle utilise la factory pour créer la map des actions.
     */
    @Override
    public void init() throws ServletException {
        actionMap = MyFactory.createActionMap(getServletContext());
    }

    /**
     * Méthode qui gère les requêtes GET.
     * Elle délègue le traitement à la méthode processRequest.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Méthode qui gère les requêtes POST.
     * Elle délègue le traitement à la méthode processRequest.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Méthode qui traite la requête HTTP.
     * Elle récupère l'action à exécuter, instancie dynamiquement la classe correspondante,
     * et redirige vers la vue appropriée.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération du chemin de la requête pour déterminer l'action à exécuter
        String path = request.getServletPath();

        // Suppression du premier caractère '/' et de l'extension '.do' pour obtenir le nom de l'action
        String actionName = path.substring(1, path.lastIndexOf(".do"));

        // Initialisation de l'action et de la vue
        Action action = null;
        String view = "";

        // Récupération de la classe associée dans la map via la factory
        Class<? extends Action> actionClass = actionMap.get(actionName);

        // Si la classe d'action est trouvée, on l'instancie
        if(actionClass != null) {
            try {
                // Instanciation dynamique de l'action
                action = actionClass.newInstance();
                // Exécution de l'action pour récupérer l'URL de la vue
                view = action.execute(request, response);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ServletException("Erreur lors de l'instanciation de l'action : " + actionName, e);
            }
        } else {
            // Si l'action n'est pas trouvée
            view = "index.jsp";
        }

        // Dispatch vers la vue retournée par l'action
        RequestDispatcher dispatcher = request.getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
}
