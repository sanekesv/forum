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
 * Created by Александр on 03.04.14.
 */
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String post = (String)request.getParameter("post");
        String username = (String) request.getParameter("username");
        post = post.replaceAll("\\n", "<\\br>");
        System.out.println(post);
        addMessageToDB(post, username);
        doGet(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index");
        dispatcher.forward(request, response);

    }

    private void addMessageToDB(String post, String username) {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Egorchev", "postgres", "postgres");
            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO forum(author, comment) VALUES (\'"+ username+"\', \'" +  post +"\')");


            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }     // Timestamp

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
