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
            <h1 class="page-header">Creation d'un nouveau element
                <small>Element</small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form id ="add_element" >
            <table>
                <tr>
                    <td>Title: </td>
                    <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><input type="text" name="description" required></td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td>
                    <select name="status">
                        <option value="0">A faire</option>
                        <option value="1">Fait</option>
                        <option value="2">A supprimer</option>
                    </select>
                </td>
                </tr>
                <tr>
                    <td>Tag: </td>
                    <td><input type="text" name="tag" required></td>
                </tr>
                <tr>
                    <td>Liste:</td>
                    <td>
                        <select name="idList">
                        <#list lists as list>
                            <#if list??>
                                <option value="${list.id}">${list.title}</option>
                            </#if>
                        </#list>
                        </select>
                    </td>
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
	<#include "/templates/footer.ftl">
</footer>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/app.js"></script>
</body>
</html>
