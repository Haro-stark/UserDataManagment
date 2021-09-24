package com.example.userdata;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.userdata.User;
import com.example.userdata.UserDAO;

@WebServlet(name = "UserServlet", value = "/userInfo")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getServletPath();
        System.out.println("request.getServletPath() == " + action);

        try {
            switch (action) {
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> userList = userDAO.selectAllUser();
        for (User u : userList) {
            System.out.println("name: " + u.name);
        }
        request.setAttribute("userList", userList);
        //getRequestDispatcher returns the instance of a request Dispatcher class
        RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
        //".forward" forwards a request from a servlet to another dispatcher
        rd.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user-edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        System.out.println("Getting params in update User: "+email);
        User user = new User(id, name, email, country);
        userDAO.updateUser(user);
        response.sendRedirect("userInfo");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("userInfo");

    }
}
