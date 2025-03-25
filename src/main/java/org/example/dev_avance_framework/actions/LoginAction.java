package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérification basique : admin/admin
        if("admin".equals(username) && "admin".equals(password)) {
            UserBean user = new UserBean();
            user.setUsername(username);
            user.setAttribut1("default1");
            user.setAttribut2("default2");
            request.getSession().setAttribute("user", user);
            return "page1.jsp";
        } else {
            return "erreur.jsp";
        }
    }
}
