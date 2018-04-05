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
            Username : <input type="text" name="username"/>
            Password : <input type="password" name="password"/>
            <input type="submit" value="Valider">
            <input type="reset" value="Reset">
        </form>
    </div>


</div>
<!-- /.container -->

<footer>
	<#include "/footer.ftl">
</footer>


<!-- jQuery -->
<script src="js/jquery-1.11.3.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>
<script src="js/app.js"></script>

</body>

</html>
