package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

public class CheckAttributesAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String attr1 = request.getParameter("attribut1");
        String attr2 = request.getParameter("attribut2");

        if (attr1 == null || attr1.trim().isEmpty() ||
                attr2 == null || attr2.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Les deux attributs doivent être remplis !");
            return "page1.jsp";
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            UserBean user = (UserBean) session.getAttribute("user");
            if (user != null) {
                user.setAttribut1(attr1);
                user.setAttribut2(attr2);
            }
        }
        return "working.jsp";
    }
}
