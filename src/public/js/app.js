$(document).ready(function() {
    $("#form_signIn").submit(function () {
        var name = document.forms["signIn"]["name"].value;
        var firstname = document.forms["signIn"]["firstname"].value;
        $.ajax({
            type: "POST",
            url: "/signIn",
            data: "name=" + name + "&firstname=" + firstname,
            success: function(response) {
                if(JSON.stringify(response)!=null){
                    window.location.href = "/index";
                }else{
                    console.log("Bad user");
                    $("#error").val("Bad user !")
                }
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
        return false;
    });

    $(".check_element").click(function(){
        var id = $(this).attr('id');
        var status = $(this).val();
        if(status==0){
            status = 1;
        }else if(status==1){
            status = 2;
        }else{
            status = 0;
        }
        $.ajax({
            type: "PUT",
            url: "/element/status",
            data: "id=" + id + "&status=" + status,
            success: function(response) {
                window.location.href = "/";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $(".deleteUser").click(function(){
        var id = $(this).attr('id');
        $.ajax({
            type: "DELETE",
            url: "/user/"+id,
            data: id,
            success: function(response){
                window.location.href = "/list-users";
            },
            error: function(error){
                console.log("ERROR");
            }
        });
    });

    $(".deleteList").click(function(){
        var id = $(this).attr('id');
        $.ajax({
            type: "DELETE",
            url: "/list/"+id,
            data: id,
            success: function(response){
                window.location.href = "/list-lists";
            },
            error: function(error){
                console.log("ERROR");
            }
        });
    });

    $(".deleteElement").click(function(){
        var id = $(this).attr('id');
        $.ajax({
            type: "DELETE",
            url: "/element/"+id,
            data: id,
            success: function(response){
                window.location.href = "/list-elements";
            },
            error: function(error){
                console.log("ERROR");
            }
        });
    });

    $(".modifyUser").click(function(){
        var id = $(this).attr('id');
        window.location.href = "/modify-user/"+id;
    });

    $(".modifyList").click(function(){
        var id = $(this).attr('id');
        window.location.href = "/modify-list/"+id;
    });

    $(".modifyElement").click(function(){
        var id = $(this).attr('id');
        window.location.href = "/modify-element/"+id;
    });


    $("#add_user").submit(function(e){
        e.preventDefault();
        var name = document.forms["add_user"]["name"].value;
        var firstname = document.forms["add_user"]["firstname"].value;
        var role = document.forms["add_user"]["role"].value;
        $.ajax({
            type: "POST",
            url: "/user/",
            data: "name=" + name + "&firstname=" + firstname+"&role="+role,
            success: function(response) {
                window.location.href = "/list-users";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $("#add_list").submit(function(e){
        e.preventDefault();
        var title = document.forms["add_list"]["title"].value;
        var description = document.forms["add_list"]["description"].value;
        $.ajax({
            type: "POST",
            url: "/list/",
            data: "title=" + title + "&description=" + description,
            success: function(response) {
                window.location.href = "/list-lists";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $("#add_element").submit(function(e){
        e.preventDefault();
        var title = document.forms["add_element"]["title"].value;
        var description = document.forms["add_element"]["description"].value;
        var status = document.forms["add_element"]["status"].value;
        var tag = document.forms["add_element"]["tag"].value;
        var idList = document.forms["add_element"]["idList"].value;
        $.ajax({
            type: "POST",
            url: "/element/",
            data: "idList=" + idList +"&title=" + title + "&description=" + description+"&status="+status+"&tag="+tag,
            success: function(response) {
                window.location.href = "/list-elements";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $("#modify_user").submit(function(e){
        e.preventDefault();
        var id = document.forms["modify_user"]["id"].value;
        var name = document.forms["modify_user"]["name"].value;
        var firstname = document.forms["modify_user"]["firstname"].value;
        var role = document.forms["modify_user"]["role"].value;
        $.ajax({
            type: "PUT",
            url: "/user/",
            data: "id=" + id +"&name=" + name + "&firstname=" + firstname+"&role="+role,
            success: function(response) {
                window.location.href = "/list-users";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $("#modify_element").submit(function(e){
        e.preventDefault();
        var title = document.forms["modify_element"]["title"].value;
        var description = document.forms["modify_element"]["description"].value;
        var status = document.forms["modify_element"]["status"].value;
        var tag = document.forms["modify_element"]["tag"].value;
        var idList = document.forms["modify_element"]["idList"].value;
        var id = document.forms["modify_element"]["id"].value;
        $.ajax({
            type: "PUT",
            url: "/element/",
            data: "id=" + id +"&idList=" + idList +"&title=" + title + "&description=" + description+"&status="+status+"&tag="+tag,
            success: function(response) {
                window.location.href = "/list-elements";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $("#modify_list").submit(function(e){
        e.preventDefault();
        var title = document.forms["modify_list"]["title"].value;
        var description = document.forms["modify_list"]["description"].value;
        var id = document.forms["modify_list"]["id"].value;
        $.ajax({
            type: "PUT",
            url: "/list/",
            data: "id=" + id +"&title=" + title + "&description=" + description,
            success: function(response) {
                window.location.href = "/list-lists";
            },
            error: function(error) {
                console.log("ERROR");
            }
        });
    });

    $(".deleteelement").click(function(){
        var id = $(this).attr('id');
        console.log("id"+id);
        $.ajax({
            type: "DELETE",
            url: "/element/"+id,
            data: id,
            success: function(response){
                window.location.href = "/";
            },
            error: function(error){
                console.log("ERROR");
            }
        });
    });
});