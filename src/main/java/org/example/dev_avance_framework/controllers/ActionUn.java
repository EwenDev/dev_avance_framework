package org.example.dev_avance_framework.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import org.example.dev_avance_framework.beans.UserBean;

@WebServlet(name="ActionUn", urlPatterns={"/ActionUn"})
public class ActionUn extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        if(user != null){
            user.setAttribut1(request.getParameter("attribut1"));
            user.setAttribut2(request.getParameter("attribut2"));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("page1.jsp");
        dispatcher.forward(request, response);
    }
}
