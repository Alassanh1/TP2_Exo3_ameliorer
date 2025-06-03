<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    String user = (String) session.getAttribute("utilisateur");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #FFDEE9, #B5FFFC);
            padding: 40px;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        .btn-container {
            margin-top: 40px;
        }
        .btn {
            display: inline-block;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 15px 25px;
            margin: 10px;
            border-radius: 8px;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .logout {
            margin-top: 20px;
            color: red;
        }
    </style>
</head>
<body>

    <h1>Bienvenue <%= user %> 👋</h1>

    <div class="btn-container">
        <a href="inscription.jsp" class="btn">Ajouter un membre</a>
        <a href="MembreServlet" class="btn">Voir la liste des membres</a>
    </div>

    <form action="logout.jsp" method="post">
        <input type="submit" value="Se déconnecter" class="logout">
    </form>

</body>
</html>
