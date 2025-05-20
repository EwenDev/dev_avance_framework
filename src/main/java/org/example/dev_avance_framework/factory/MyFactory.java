package org.example.dev_avance_framework.factory;

import java.util.HashMap;
import java.util.Map;

import org.example.dev_avance_framework.actions.*;

public class MyFactory {
    public static Map<String, Class<? extends Action>> createActionMap() {
        Map<String, Class<? extends Action>> actionMap = new HashMap<>();
        actionMap.put("ActionDebut", ActionDebut.class);
        actionMap.put("ActionUn", ActionUn.class);
        actionMap.put("LoginAction", LoginAction.class);
        actionMap.put("LogoutAction", LogoutAction.class);
        actionMap.put("CheckAttributesAction", CheckAttributesAction.class);
        return actionMap;
    }
}
