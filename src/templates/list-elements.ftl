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
            <h1 class="page-header">Liste des elements
                <small>Element</small>
            </h1>
        </div>
        <a href="/new-element"><button>Ajouter un element</button></a>
    </div><br>
    <div class="row">
        <table>
            <tr height="30px">
                <th width="100px" style="border-style: solid;text-align: center;">Title</th><th width="100px" style="border-style: solid;text-align: center;">Description</th><th width="100px" style="border-style: solid;text-align: center;">Status</th><th width="100px" style="border-style: solid;text-align: center;">Tag</th><th width="100px" style="border-style: solid;text-align: center;">Id List</th><th style="border-style: solid;text-align: center;" width="100px">Modifier</th><th style="border-style: solid;text-align: center;" width="100px">Supprimer</th>
            </tr>
           <#list elements as element>
               <#if element??>
                    <tr style="border-style: solid;" height="30px">
                        <td>${element.title}</td><td>${element.description}</td><td>${element.status}</td><td>${element.tag}</td><td>${element.idList}</td><td style="text-align: center;"><button id="${element.id}" class="modifyElement">Modifier</button></td><td style="text-align: center;"><button id="${element.id}" class="deleteElement">Supprimer</button></td>
                    </tr>
                </#if>
           </#list>
        </table>
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
