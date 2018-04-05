<!DOCTYPE html>
<html lang="fr">
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
            <h1 class="page-header">Bienvenue sur KeepList
                <small></small>
            </h1>
        </div>
    </div>
    <div class="row">
        <#if lists??>
            <#list lists as list>
                    <div class="col-md-6 portfolio-item">
                        <#include "/list.ftl">
                    </div>
            </#list>
        </#if>
    </div>
</div>

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
