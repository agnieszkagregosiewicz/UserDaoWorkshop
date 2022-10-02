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
        request.getSession().setAttribute("message", "Usunięto użytkownika o id: " + idS);
        response.sendRedirect(request.getContextPath() +"/user/list");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
