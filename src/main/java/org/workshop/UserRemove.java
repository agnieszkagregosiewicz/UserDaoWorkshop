package org.workshop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserRemove", value = "/user/remove")
public class UserRemove extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//request.getRequestDispatcher(request.getContextPath() + "/users/list.jsp").forward(request, response);
        String idS = request.getParameter("id");
        int id = Integer.parseInt(idS);
        UserDao.delete(id);
        try {
            User[] users = UserDao.findAll();
            for (User u : users) {
                System.out.println(u);
            }
            request.setAttribute("users", UserDao.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() +"/user/list");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
