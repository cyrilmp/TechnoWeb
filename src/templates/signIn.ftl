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
            <h1 class="page-header">Bienvenue sur KeepList
                <small></small>
            </h1>
        </div>
    </div>
    <div class="row">
        <form id="form_signIn" name="signIn" method="post">
            <p id="error"></p>
            Nom : <input type="text" name="name"/>
            Prénom : <input type="text" name="firstname"/>
            <input type="submit" value="Valider">
            <input type="reset" value="Reset">
        </form>
    </div>
    <!-- /.row -->
    <!-- Pagination -->
    <div class="row text-center">
        <div class="col-lg-12">
            <ul class="pagination">
                <li>
                    <a href="#">&laquo;</a>
                </li>
                <li class="active">
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">&raquo;</a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /.row -->

</div>
<!-- /.container -->

<footer>
	<#include "/templates/footer.ftl">
</footer>


<!-- jQuery -->
<script src="js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="js/app.js"></script>

</body>

</html>
