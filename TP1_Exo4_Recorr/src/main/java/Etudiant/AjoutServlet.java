package Etudiant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/ajout")
public class AjoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Configuration de la base de données
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/annuaire_etudiants_db";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = ""; // ⚠️ Remplace par ton mot de passe MySQL

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Récupérer les données du formulaire
        String prenom = req.getParameter("prenom");
        String nom = req.getParameter("nom");
        int age = Integer.parseInt(req.getParameter("age"));
        String niveau = req.getParameter("niveau");
        String email = req.getParameter("email");
        String sexe = req.getParameter("sexe");

        // Connexion à la base de données + insertion
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Charger le driver
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String sql = "INSERT INTO etudiants (prenom, nom, age, niveau, email, sexe) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, prenom);
            ps.setString(2, nom);
            ps.setInt(3, age);
            ps.setString(4, niveau);
            ps.setString(5, email);
            ps.setString(6, sexe);

            ps.executeUpdate();

            ps.close();
            conn.close();

            // Rediriger vers une page de confirmation ou liste
            resp.sendRedirect("list"); // tu peux aussi afficher un message personnalisé ici

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h3>Erreur lors de l'ajout de l'étudiant.</h3>");
        }
    }
}
