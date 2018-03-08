<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Portfolio, 2 Column</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <#include "/templates/menu.ftl">
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Creation d'un nouveau utilisateur
                <small>User</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form action="/user/add" method="post">
            <p>Nom: <input type="text" name="name"></p>
            <p>Prenom: <input type="text" name="firstname"></p>
            <p>Role:
                <select name="role">
                    <option value="admin">Admin</option>
                    <option value="user">User</option>
                </select>
            <p>
                <input type="submit" value="Submit">
                <input type="reset" value="Reset">
            </p>
        </form>
    </div>

</div>
<footer>
	<#include "/templates/footer.ftl">
</footer>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
