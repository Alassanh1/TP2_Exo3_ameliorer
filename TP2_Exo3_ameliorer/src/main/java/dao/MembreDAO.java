package dao;

import model.Membre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/clubdb";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "";

    public void ajouterMembre(Membre m) {
        String sql = "INSERT INTO membre (id, nom, prenom, profession, sexe, mail, dateAdhesion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getId());
            stmt.setString(2, m.getNom());
            stmt.setString(3, m.getPrenom());
            stmt.setString(4, m.getProfession());
            stmt.setString(5, m.getSexe());
            stmt.setString(6, m.getMail());
            stmt.setString(7, m.getDateAdhesion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Membre> getAllMembres() {
        List<Membre> liste = new ArrayList<>();
        String sql = "SELECT * FROM membre";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Membre m = new Membre(
                    rs.getString("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("profession"),
                    rs.getString("sexe"),
                    rs.getString("mail"),
                    rs.getString("dateAdhesion")
                );
                liste.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Membre getMembreById(String id) {
        String sql = "SELECT * FROM membre WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Membre(
                    rs.getString("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("profession"),
                    rs.getString("sexe"),
                    rs.getString("mail"),
                    rs.getString("dateAdhesion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateMembre(Membre m) {
        String sql = "UPDATE membre SET nom=?, prenom=?, profession=?, sexe=?, mail=?, dateAdhesion=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNom());
            stmt.setString(2, m.getPrenom());
            stmt.setString(3, m.getProfession());
            stmt.setString(4, m.getSexe());
            stmt.setString(5, m.getMail());
            stmt.setString(6, m.getDateAdhesion());
            stmt.setString(7, m.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMembre(String id) {
        String sql = "DELETE FROM membre WHERE id=?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
