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
            <h1 class="page-header">Creation d'une nouvelle liste
                <small>User</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form action="/list/add" method="post">
            <p>Titre: <input type="text" name="title"></p>
            <p>Description: <input type="text" name="description"></p>
            <table>
                <tr>
                    <td>title</td><td>description</td><td>Status</td><td>Tag</td>
                </tr>
                <tr>
                    <td><input type="text" name="title" /></td><td><input type="text" name="description" /></td><td><input type="text" name="tag" /></td><td><input type="text" name="status" /></td>
                </tr>
            </table>
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
