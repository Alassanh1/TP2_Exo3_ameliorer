<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="membre" type="model.Membre" scope="request" />
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier un membre</title>
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
        form {
            max-width: 500px;
            margin: auto;
            background: #fff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type="text"],
        input[type="email"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="radio"] {
            margin-right: 5px;
        }
        input[type="submit"] {
            width: 100%;
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h2>Modifier les informations du membre</h2>
<form action="MembreServlet" method="post">
    <input type="hidden" name="id" value="${membre.id}" />
    Prénom : <input type="text" name="prenom" value="${membre.prenom}" required><br/>
    Nom : <input type="text" name="nom" value="${membre.nom}" required><br/>
    Profession :
    <select name="profession">
        <option${membre.profession == 'Étudiant' ? ' selected' : ''}>Étudiant</option>
        <option${membre.profession == 'Enseignant' ? ' selected' : ''}>Enseignant</option>
        <option${membre.profession == 'Technicien' ? ' selected' : ''}>Technicien</option>
    </select><br/>
    Sexe :
    <input type="radio" name="sexe" value="Homme" ${membre.sexe == 'Homme' ? 'checked' : ''}> Homme
    <input type="radio" name="sexe" value="Femme" ${membre.sexe == 'Femme' ? 'checked' : ''}> Femme<br/>
    Email : <input type="email" name="mail" value="${membre.mail}" required><br/>
    Date d'adhésion : <input type="date" name="dateAdhesion" value="${membre.dateAdhesion}" required><br/>
    <input type="submit" name="update" value="Mettre à jour">
</form>

</body>
</html>
