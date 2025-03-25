package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

public class LogoutAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = "";
        if(session != null) {
            UserBean user = (UserBean) session.getAttribute("user");
            if(user != null) {
                username = user.getUsername();
            }
            session.invalidate();
        }
        request.setAttribute("username", username);
        return "goodbye.jsp";
    }
}
