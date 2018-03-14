function validateForm(){
    alert("coucou");
    var name = document.forms["signIn"]["name"].value;
    var firstname = document.forms["signIn"]["firstname"].value;
    return true;
   /* $.post( "/signIn", { 'name': name, 'firstname': firstname}
        .done(function() {
            window.location.href = "/index";
        })
        .fail(function() {
            $('#error').text('User not exist !'); 
        })
    );*/
}