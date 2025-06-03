package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final String USER = "admin";
    private static final String PASS = "admin";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (USER.equals(username) && PASS.equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("utilisateur", username);
            resp.sendRedirect("accueil.jsp");
        } else {
            req.setAttribute("erreur", "Identifiants incorrects !");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
