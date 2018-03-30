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
});