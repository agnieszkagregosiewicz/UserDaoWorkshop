package org.workshop;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "Servlet", value = "/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("users", UserDao.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        getServletContext().getRequestDispatcher(request.getContextPath() + "/users/list.jsp").forward(request, response);
        request.getSession().setAttribute("message", null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
