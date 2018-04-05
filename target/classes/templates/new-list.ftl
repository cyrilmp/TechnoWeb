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
            <h1 class="page-header">Creation d'une nouvelle liste
                <small>List</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form id ="add_list">
            <p>Titre: <input type="text" name="title" required></p>
            <p>Description: <input type="text" name="description" required></p>
            <p>
                <input type="submit" value="Submit">
                <input type="reset" value="Reset">
            </p>
        </form>
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
