package org.example.dev_avance_framework.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

public class ActionUn implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user != null) {
            user.setAttribut1(request.getParameter("attribut1"));
            user.setAttribut2(request.getParameter("attribut2"));
        }
        return "page1.jsp";
    }
}
