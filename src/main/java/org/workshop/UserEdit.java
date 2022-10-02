package org.workshop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    User user = new User();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ids = request.getParameter("id");
        int id = Integer.parseInt(ids);
        request.getSession().setAttribute("id", id);
        request.setAttribute("user", UserDao.read(id));

        getServletContext().getRequestDispatcher(request.getContextPath() + "/users/edit2.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] name = request.getParameterValues("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //String id = request.getParameter("id");
//        if (name == null || name.isBlank() || email == null || email.isBlank() || password.isBlank() || password == null) {
//            response.getWriter().append("Pola nie mogą być puste");
//            getServletContext().getRequestDispatcher(request.getContextPath() + "/users/add.jsp").forward(request, response);
//        } else {
        user.setName(String.valueOf(name));
        user.setPassword(password);
        user.setEmail(email);
        user.setId((int) request.getSession().getAttribute("id"));
        UserDao.update(user);
        String message = "Użytkownik zmieniony";
        //request.getSession().setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}
