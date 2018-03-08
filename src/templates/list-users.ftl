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
            <h1 class="page-header">Liste des utilisateurs
                <small>User</small>
            </h1>
        </div>
        <a href="/newuser"><button>Ajouter un utilisateur</button></a>
    </div>
    <div class="row">
        <table>
           <#list users as user>
                <tr>
                    <td>${user.name}</td><td>${user.firstname}</td><td>${user.role}</td><td><input type="button" value="Modifier"></td>
                </tr>
           </#list>
        </table>
    </div>

</div>
<footer>
	<#include "/templates/footer.ftl">
</footer>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
