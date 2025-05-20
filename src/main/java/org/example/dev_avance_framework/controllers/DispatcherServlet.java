package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;
import org.example.dev_avance_framework.actions.Action;
import org.example.dev_avance_framework.factory.MyFactory;

public class DispatcherServlet extends HttpServlet {

    private Map<String, Class<? extends Action>> actionMap;

    @Override
    public void init() throws ServletException {
        actionMap = MyFactory.createActionMap(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String actionName = path.substring(1, path.lastIndexOf(".do"));

        Action action = null;
        String view = "";

        // Récupération de la classe associée dans la map via la factory
        Class<? extends Action> actionClass = actionMap.get(actionName);
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
