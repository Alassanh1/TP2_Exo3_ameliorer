<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Liste des membres</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(to right, #eef2f3, #8e9eab);
            padding: 20px;
            margin: 0;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        table {
            width: 90%;
            margin: auto;
            border-collapse: collapse;
            background: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f8f9fa;
        }
        a {
            color: #007bff;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        .btn {
            display: block;
            width: 200px;
            margin: 30px auto;
            text-align: center;
            background-color: #28a745;
            color: white;
            padding: 10px;
            border-radius: 8px;
            text-decoration: none;
        }
        .btn:hover {
            background-color: #218838;
        }
         .btn-container {
            margin-top: 40px;
        }
    </style>
</head>
<body>

<h2>Liste des membres du club</h2>

<table>
    <tr>
        <th>ID</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Profession</th>
        <th>Sexe</th>
        <th>Email</th>
        <th>Date d'adhésion</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="m" items="${membres}">
        <tr>
            <td>${m.id}</td>
            <td>${m.nom}</td>
            <td>${m.prenom}</td>
            <td>${m.profession}</td>
            <td>${m.sexe}</td>
            <td>${m.mail}</td>
            <td>${m.dateAdhesion}</td>
            <td>
                <a href="MembreServlet?action=modif&id=${m.id}">Modifier</a> |
                <a href="MembreServlet?action=suppr&id=${m.id}" onclick="return confirm('Confirmer la suppression ?');">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
<div class="btn-container">
<a href="inscription.jsp" class="btn">➕ Ajouter un membre</a>
<a href="logout.jsp" class="btn">Deconnexion</a>
</div>
</body>
</html>
