package Etudiant;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/list")
public class ListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/annuaire_etudiants_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; // ⚠️ Remplace si besoin

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background: linear-gradient(to right, rgb(2, 255, 0), rgb(255, 0, 9)); }");
        out.println("h1 { color: #333; text-align: center; }");
        out.println("form { max-width: 600px; margin: 0 auto; background-color: rgb(255, 252, 0); padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println(".form-group { margin-bottom: 15px; }");
        out.println("label { display: block; margin-bottom: 5px; font-weight: bold; }");
        out.println("input[type='text'], input[type='number'], input[type='email'], select { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 4px; box-sizing: border-box; }");
        out.println(".radio-group { display: flex; gap: 15px; }");
        out.println(".radio-option { display: flex; align-items: center; }");
        out.println(".radio-option input { margin-right: 5px; }");
        out.println(".submit-btn { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; display: block; margin: 0 auto; margin-top: 20px; }");
        out.println(".submit-btn:hover { background-color: #45a049; }");
        out.println("table { width: 90%; margin: 20px auto; border-collapse: collapse; background: white; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
        out.println("th, td { padding: 10px; text-align: left; border: 1px solid #ddd; }");
        out.println("th { background-color: #f0f0f0; }");
        out.println("a { text-decoration: none; color: #4CAF50; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println(".add-btn { display: block; width: fit-content; margin: 20px auto; padding: 10px 15px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; text-align: center; }");
        out.println(".add-btn:hover { background-color: #45a049; }");
        out.println("</style>");



        out.println("<html><head><title>Liste des étudiants</title>");
        out.println("<style>");
        out.println("table { background-color: yellow; width: 90%; margin: 20px auto; border-collapse: collapse; box-shadow: 0 0 10px rgba(0,0,0,0.2); }");
        out.println("th, td { padding: 10px; border: 1px solid #333; text-align: left; }");
        out.println("th { background-color: #fff000; }");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(to right, rgb(2, 255, 0), rgb(255, 0, 9)); padding: 20px; }");
        out.println("h2 { text-align: center; color: #333; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2>Annuaire des étudiants</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Prénom</th><th>Nom</th><th>Âge</th><th>Niveau</th><th>Email</th><th>Sexe</th><th>Actions</th></tr>");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "SELECT * FROM etudiants";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + rs.getString("prenom") + "</td>");
                out.println("<td>" + rs.getString("nom") + "</td>");
                out.println("<td>" + rs.getInt("age") + "</td>");
                out.println("<td>" + rs.getString("niveau") + "</td>");
                out.println("<td>" + rs.getString("email") + "</td>");
                out.println("<td>" + rs.getString("sexe") + "</td>");
                out.println("<td>");
                out.println("<a href='modifier?id=" + id + "'>Modifier</a> | ");
                out.println("<a href='supprimer?id=" + id + "' onclick=\"return confirm('Supprimer cet étudiant ?')\">Supprimer</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<tr><td colspan='8'>Erreur de connexion à la base de données</td></tr>");
            e.printStackTrace();
        }

        out.println("</table><br>");
        out.println("<a href='ajout.html' class='add-btn'>Ajouter un nouvel étudiant</a>");
        out.println("</body></html>");
        out.close();
    }
}

