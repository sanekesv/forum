package serv;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Александр on 27.04.14.
 */
public class RegistrServlet extends HttpServlet {

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

        try {
            if (request.getParameter("password").equals(request.getParameter("repassword"))) {
                Statement statement = getStatement();
//            statement.execute("INSERT INTO users(\n" +
//                    "login, password)\n" +
//                    "VALUES (" + "\'" + request.getParameter("username") + "\', \'" + request.getParameter("password") + "\')");
            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
