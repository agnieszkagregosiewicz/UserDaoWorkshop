package org.workshop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserAdd", value = "/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(request.getContextPath() + "/users/add.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        if (name == null || name.isBlank() || email == null || email.isBlank() || password.isBlank() || password == null) {
//            response.getWriter().append("Pola nie mogą być puste");
//            getServletContext().getRequestDispatcher(request.getContextPath() + "/users/add.jsp").forward(request, response);
//        } else {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        UserDao.create(user);
        System.out.println(user);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }

}
