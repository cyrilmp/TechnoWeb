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
        <#include "/menu.ftl">
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Liste des utilisateurs
                <small>User</small>
            </h1>
        </div>
        <a href="/new-user"><button>Ajouter un utilisateur</button></a>
    </div><br>
    <div class="row">
        <table>
            <tr height="30px">
                <th width="100px" style="border-style: solid;text-align: center;">Name</th><th width="100px" style="border-style: solid;text-align: center;">Firstname</th><th style="border-style: solid;text-align: center;" width="100px">Role</th><th style="border-style: solid;text-align: center;" width="100px">Modifier</th><th style="border-style: solid;text-align: center;" width="100px">Supprimer</th>
            </tr>
           <#list users as user>
               <#if user??>
                    <tr style="border-style: solid;" height="30px">
                        <td>${user.name}</td><td>${user.firstname}</td><td>${user.role}</td><td style="text-align: center;"><button id="${user.id}" class="modifyUser">Modifier</button></td><td style="text-align: center;"><button id="${user.id}" class="deleteUser">Supprimer</button></td>
                    </tr>
                </#if>
           </#list>
        </table>
    </div>

</div>
<footer>
	<#include "/footer.ftl">
</footer>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>
