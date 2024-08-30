
function mostrarCampoCantidad() {
    var menoresACargo = document.getElementById("menoresACargo").value;
    var cantidadMenores = document.getElementById("cantidadMenores")
    if (menoresACargo === "si") {
        cantidadMenores.style.display = "block";
    } else {
        cantidadMenores.style.display = "none";
    }
}

function mostrarCampoDomicilio() {
    var situActual = document.getElementById("situacionActual").value;
    var domicilioActu = document.getElementById("domicilioActual");
    if (situActual === "no") {
        domicilioActu.style.display = "block";
    } else {
        domicilioActu.style.display = "none";
    }
}

function mostrarCampoIngreseDNI() {
    var tipoDNI = document.getElementById("tipoDNI").value;
    var ingreseDNI = document.getElementById("ingresarDocumento");
    if (tipoDNI === "DNI" ||tipoDNI === "PASAPORTE" ||tipoDNI === "LIBRETA") {
        ingreseDNI.style.display = "block";
    } else {
        ingreseDNI.style.display = "none";
    }
}

function enviarDatos(){
    var validadorNombre = document.getElementById("nombreV").value;
    var validadorApellido = document.getElementById("apellidoV").value;
    var validadorNacimiento = document.getElementById("fechaNacimientoV").value;
    var validadorDNI = document.getElementById("campoDNI").value;

    if(validadorNombre === "" || validadorApellido === "" || validadorNacimiento === "" ||validadorDNI === "" ){
        alert("Complete todos los campos");
    }else{
        alert("Registro Exitoso");
    }
}

