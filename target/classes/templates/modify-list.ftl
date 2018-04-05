<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Portfolio, 2 Column</title>
    <link href="http://127.0.0.1:4567/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://127.0.0.1:4567/css/custom.css" rel="stylesheet">
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
            <h1 class="page-header">Modification d'une liste
                <small>List</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form id ="modify_list" >
            <input type="text" name="id" value="${list.id}" hidden>
            <table>
                <tr>
                    <td>Title: </td>
                    <td><input type="text" name="title" value="${list.title}" required></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><input type="text" name="description" value="${list.description}" required></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </div>

</div>
<footer>
	<#include "/footer.ftl">
</footer>
<script src="http://127.0.0.1:4567/js/jquery-1.11.3.min.js"></script>
<script src="http://127.0.0.1:4567/js/bootstrap.min.js"></script>
<script src="http://127.0.0.1:4567/js/app.js"></script>
</body>
</html>
