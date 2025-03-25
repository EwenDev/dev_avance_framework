package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    /**
     * Exécute la logique de l'action et retourne le chemin de la vue.
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
