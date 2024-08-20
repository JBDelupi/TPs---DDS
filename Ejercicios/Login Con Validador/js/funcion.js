document.getElementById("botonEnvio").addEventListener("click",function(){
    var usuario = document.getElementById("usuario").value;
    var contrasenia = document.getElementById("password").value;

    //Hago esto para que cada vez que se presione de vuelta el boton, se borren los bordes rojos
    //y solo se vuelvan a poner rojos si algo sale mal
    document.getElementById("usuario").style.border = "none";
    document.getElementById("password").style.border = "none";


    if(usuario === '' || contrasenia === ''){
        document.getElementById("usuario").style.border = "3px solid red";
        document.getElementById("password").style.border = "3px solid red";
        alert('Por favor, complete todos los campos');
    }else if(usuario === 'admin'){
        document.getElementById("usuario").style.border = "3px solid red";
        alert('El usuario "admin" no es un usuario valido');
    }else if(contrasenia === '1234'){
        document.getElementById("password").style.border = "3px solid red";
        alert('La contraseña "1234" no es valida');
    }else if(contrasenia == 'admin'){
        document.getElementById("password").style.border = "3px solid red";
        alert('La contraseña "admin" no es valida');
    }else{
        alert('Registro Exitoso');

        var form = document.getElementById("formulario");

        // Pongo un borde verde al formulario
        form.style.border = "3px solid green";
        form.style.transition = "border-color 2s ease";

        // Después de 2 segundos cambia el color del borde a transparente para que se desvanezca
        // NO hago esto con el rojo de los errores para que se quede marcado el campo donde hubo un fallo
        setTimeout(function() {
            form.style.borderColor = "transparent";
        }, 2000);

    }
    
    

});