package servlet;

import dao.MembreDAO;
import model.Membre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MembreServlet")
public class MembreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MembreDAO dao = new MembreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String profession = request.getParameter("profession");
        String sexe = request.getParameter("sexe");
        String mail = request.getParameter("mail");
        String date = request.getParameter("dateAdhesion");

        Membre membre = new Membre(id, nom, prenom, profession, sexe, mail, date);

        if (request.getParameter("update") != null) {
            dao.updateMembre(membre);
            response.sendRedirect("MembreServlet");
        } else {
            dao.ajouterMembre(membre);
            response.sendRedirect("MembreServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if ("modif".equals(action) && id != null) {
            Membre membre = dao.getMembreById(id);
            request.setAttribute("membre", membre);
            request.getRequestDispatcher("edition.jsp").forward(request, response);
        } else if ("suppr".equals(action) && id != null) {
            dao.deleteMembre(id);
            response.sendRedirect("MembreServlet");
        } else {
            List<Membre> membres = dao.getAllMembres();
            request.setAttribute("membres", membres);
            request.getRequestDispatcher("liste.jsp").forward(request, response);
        }
    }
}
