package serv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Александр on 27.04.14.
 */
public class LoginServlet extends HttpServlet {

    private Statement getStatement() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/Egorchev";
        try {
            connection = DriverManager.getConnection(url, "postgres", "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection.createStatement();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        try {
            Statement statement = getStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM users as u WHERE u.login = \'"
                    + request.getParameter("username") + "\'");
            if (set.next()) {
                if (request.getParameter("password").equals(set.getString("password"))) {
                    session.setAttribute("id", set.getString("id"));
                    session.setAttribute("login", set.getString("login"));
                    doGet(request,response);
                } else {
                    request.setAttribute("error", "Login or password don\'t match");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }

            } else {
                //Add error message
                request.setAttribute("error", "Login or password don\'t match");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        System.out.println(session.getAttribute("login"));
        if (session.getAttribute("login") != null) {
            response.sendRedirect("mypage.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
