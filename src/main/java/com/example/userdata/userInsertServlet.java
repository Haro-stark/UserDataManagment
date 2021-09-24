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


@WebServlet(name = "UserInsertServlet", value = "/insertUser")
public class userInsertServlet extends HttpServlet  {
    UserDAO userDAO;
    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(name, email, country);

        userDAO.insertUser(user);
        response.sendRedirect("userInfo");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

}
