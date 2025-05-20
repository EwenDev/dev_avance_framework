package org.example.dev_avance_framework.factory;

import jakarta.servlet.ServletContext;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.example.dev_avance_framework.actions.Action;

public class MyFactory {

    public static Map<String, Class<? extends Action>> createActionMap(ServletContext context) {
        Map<String, Class<? extends Action>> actionMap = new HashMap<>();
        try (InputStream is = context.getResourceAsStream("/WEB-INF/actions-config.xml")) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("action");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element) nodes.item(i);
                String name  = elem.getAttribute("name");
                String cls   = elem.getAttribute("class");
                @SuppressWarnings("unchecked")
                Class<? extends Action> actionClass =
                        (Class<? extends Action>) Class.forName(cls);
                actionMap.put(name, actionClass);
            }
        } catch (Exception e) {
            throw new RuntimeException("Impossible de charger actions-config.xml", e);
        }
        return actionMap;
    }
}
