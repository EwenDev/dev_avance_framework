package org.example.dev_avance_framework.factory;

import jakarta.servlet.ServletContext;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.example.dev_avance_framework.actions.Action;

/**
 * MyFactory est une classe utilitaire qui charge les actions à partir d'un fichier XML de configuration.
 * Elle crée une map associant les noms d'actions à leurs classes respectives.
 */
public class MyFactory {

    public static Map<String, Class<? extends Action>> createActionMap(ServletContext context) {

        // Map pour stocker les associations entre les noms d'actions et les classes d'actions
        Map<String, Class<? extends Action>> actionMap = new HashMap<>();

        // Chargement du fichier XML de configuration des actions
        try (InputStream is = context.getResourceAsStream("/WEB-INF/actions-config.xml")) {

            // Nouvelle instance de DocumentBuilderFactory pour parser le fichier XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            // Création d'un DocumentBuilder pour lire le fichier XML
            DocumentBuilder db = dbf.newDocumentBuilder();

            // Parsing du fichier XML et création d'un Document
            Document doc = db.parse(is);

            // Normalisation du document pour éviter les problèmes de structure
            NodeList nodes = doc.getElementsByTagName("action");

            // Parcours des noeuds "action" pour remplir la map
            for (int i = 0; i < nodes.getLength(); i++) {

                // Récupération de l'élément "action" et de ses attributs
                Element elem = (Element) nodes.item(i);
                String name = elem.getAttribute("name");
                String cls  = elem.getAttribute("class");

                // Vérification que le nom et la classe ne sont pas vides
                try {
                    Class<?> raw = Class.forName(cls);
                    // Vérifie que la classe implémente bien Action
                    if (!Action.class.isAssignableFrom(raw)) {
                        context.log("MyFactory: la classe " + cls + " n'implémente pas Action");
                        continue;
                    }
                    // @SuppressWarnings est utilisé pour éviter les avertissements de type non vérifié
                    // il est sûr de faire un cast ici car on a vérifié que la classe implémente Action
                    @SuppressWarnings("unchecked")
                    Class<? extends Action> actionClass = (Class<? extends Action>) raw;

                    // Ajout de l'action à la map
                    actionMap.put(name, actionClass);
                } catch (ClassNotFoundException e) {
                    context.log("MyFactory: classe introuvable pour l'action '" + name + "' -> " + cls, e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Impossible de charger actions-config.xml", e);
        }

        // Retourne la map des actions
        return actionMap;
    }
}
