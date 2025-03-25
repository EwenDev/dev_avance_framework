package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

@WebServlet(name="LogoutAction", urlPatterns={"/LogoutAction"})
public class LogoutAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = "";
        if(session != null){
            UserBean user = (UserBean) session.getAttribute("user");
            if(user != null) {
                username = user.getUsername();
            }
            session.invalidate();
        }
        request.setAttribute("username", username);
        RequestDispatcher dispatcher = request.getRequestDispatcher("goodbye.jsp");
        dispatcher.forward(request, response);
    }
}
