package Etudiant;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/modifier")
public class ModifierServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/annuaire_etudiants_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; // Mets ton mot de passe MySQL

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "SELECT * FROM etudiants WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Formulaire pré-rempli
                out.println("<html><body>");
                out.println("<h2>Modifier l'étudiant</h2>");
                out.println("<form method='post' action='modifier'>");

                out.println("<input type='hidden' name='id' value='" + id + "' />");

                out.println("Prénom : <input type='text' name='prenom' value='" + rs.getString("prenom") + "' required><br><br>");
                out.println("Nom : <input type='text' name='nom' value='" + rs.getString("nom") + "' required><br><br>");
                out.println("Âge : <input type='number' name='age' value='" + rs.getInt("age") + "' required><br><br>");
                out.println("Niveau : <input type='text' name='niveau' value='" + rs.getString("niveau") + "' required><br><br>");
                out.println("Email : <input type='email' name='email' value='" + rs.getString("email") + "' required><br><br>");

                String sexe = rs.getString("sexe");
                out.println("Sexe : ");
                out.println("<input type='radio' name='sexe' value='Homme'" + ("Homme".equals(sexe) ? " checked" : "") + "> Homme ");
                out.println("<input type='radio' name='sexe' value='Femme'" + ("Femme".equals(sexe) ? " checked" : "") + "> Femme <br><br>");

                out.println("<input type='submit' value='Enregistrer'>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<p>Étudiant non trouvé.</p>");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<p>Erreur lors de la connexion à la base.</p>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        int age = Integer.parseInt(req.getParameter("age"));
        String niveau = req.getParameter("niveau");
        String email = req.getParameter("email");
        String sexe = req.getParameter("sexe");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "UPDATE etudiants SET prenom = ?, nom = ?, age = ?, niveau = ?, email = ?, sexe = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, prenom);
            ps.setString(2, nom);
            ps.setInt(3, age);
            ps.setString(4, niveau);
            ps.setString(5, email);
            ps.setString(6, sexe);
            ps.setInt(7, id);

            ps.executeUpdate();

            ps.close();
            conn.close();

            // Rediriger vers la liste après modification
            resp.sendRedirect("list");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h3>Erreur lors de la mise à jour de l'étudiant.</h3>");
        }
    }
}
