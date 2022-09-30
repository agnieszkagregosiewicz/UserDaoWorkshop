package org.workshop;
import org.workshop.UserDao;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Servlet", value = "/user/list")
public class UserList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher(request.getContextPath() + "/users/list.jsp").forward(request, response);
        try {
            User[] users = UserDao.findAll();
            for (User u : users) {
                System.out.println(u);
            }
            request.setAttribute("users", UserDao.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        getServletContext().getRequestDispatcher(request.getContextPath() +"/users/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
