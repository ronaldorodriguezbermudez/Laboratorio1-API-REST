/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var backBean = {
    username: null,
    password: null
};

function init() {  
    console.log("Aplicación inicializada..");
}

function getJSON(url, data, callback) {
    fetch(url, {
        method: 'POST',
        body: data
    }).then((result) => {
        return result.json();
    }).then(callback);
}

function enviarFormulario() {
    console.log("Enviando datos del formulario..");

    var datos = new FormData();

    for (property in backBean) { 
        //var username = document.getElementById("username");
        //var password = document.getElementById("password");
        let refCampo = document.getElementById(property);
        if (refCampo) {
            let valor = refCampo.value;
            if (!(typeof (valor) === 'undefined' || valor === null || valor === "")) {
                backBean[property] = valor; 
                datos.append(property, valor); 
            }
        } else { 
            datos.append(property, backBean[property]);
        }
    }

    getJSON('ServicioLogin', datos, procesarRespuesta);

    //return false; 
}

function validarUsuario(){
    var datos = new FormData();
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    datos.append('username', username);
    datos.append('password', password);
    console.log(username+' - '+password);
    getJSON('ServicioLogin', datos, procesarRespuesta);
    console.log("Enviados: "+username+' - '+password);
}

function procesarRespuesta(datos) {
    console.log(datos);
}

// Constructor de usuario
function Usuario(idUsuario, claveAcceso, rol) {
    this.idUsuario = idUsuario;
    this.claveAcceso = claveAcceso;
    this.rol = rol;
}

// Animacion de formulario login
var inputs = document.getElementsByClassName('formulario_input');
for (var i = 0; i < inputs.length; i++){
    inputs[i].addEventListener('keyup', function(){
        if(this.value.length >= 1){
            this.nextElementSibling.classList.add('fijar');
        } else{
            this.nextElementSibling.classList.remove('fijar');
        }
    });
}

