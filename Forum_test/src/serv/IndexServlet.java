package serv;

import serv.mesage.StructMessage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Александр on 01.04.14.
 */
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(true);
        ArrayList<StructMessage> list = getPosts();
        request.setAttribute("list", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("forum.jsp");
        dispatcher.forward(request, response);


    }

    private ArrayList<StructMessage> getPosts() {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
        }
        try {
            ArrayList<StructMessage> list = new ArrayList<StructMessage>();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Egorchev", "postgres", "postgres");
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM forum");
            while(set.next()) {
                StructMessage tmp = new StructMessage();
                tmp.message = set.getString("comment");
                tmp.username = set.getString("author");
                tmp.date = set.getTimestamp("date_comment").toString();
                list.add(tmp);
            }
            connection.close();

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);

    }
}
