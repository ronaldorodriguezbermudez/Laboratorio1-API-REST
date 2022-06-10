
//$(document).ready(function () {
    $("#Boton_Agregar").on("click", function () {
        BotonAgregarCurso();
    });
    function BotonAgregarCurso() {
        console.log("Entro a BotonAgregarCurso");
        var codigo = $("#Text_Codigo").val();
        var nombre = $("#Text_Nombre").val();
        var creditos = $("#Text_Creditos").val();
        var horasSemanales = $("#Text_HorasSemanales").val();
        console.log(codigo);
        console.log(nombre);
        console.log(creditos);
        console.log(horasSemanales);
        if (codigo == '' || nombre == '' || creditos == '' || horasSemanales == '') {
         alert('Llene todos los espacios antes de agregar un curso');
        }
        var dat = {"codigo": codigo, "nombre": nombre, "creditos": creditos, "horasSemanales": horasSemanales};

        $.ajax({
            type: "POST",
            url: "api/curso/",
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(dat),
            success: function (result) {
                console.log("Estado exitoso del servidor")
                if (result == "Exitoso") {
                   
                        alert( 'Curso se agrego correctamente');
                   
                } else {
                    
                      
                        alert('No se puedo realizar el mantenimiento');
                 
                }
            },
            error: function (error) {
                console.log("No se puedo recopilar los datos")
                console.log(error)
               alert('No se puedo realizar el mantenimiento');
            }
        });


    }
//})

    $("#Boton_Modificar").on("click", function () {
        BotonModificarCurso();
    });
    
        function BotonModificarCurso() {
        console.log("Entro a BotonModificarCurso");
        var codigo = $("#Text_Codigo").val();
        var nombre = $("#Text_Nombre").val();
        var creditos = $("#Text_Creditos").val();
        var horasSemanales = $("#Text_HorasSemanales").val();
        console.log(codigo);
        console.log(nombre);
        console.log(creditos);
        console.log(horasSemanales);
        if (codigo == '' || nombre == '' || creditos == '' || horasSemanales == '') {
         alert('Llene todos los espacios antes de agregar un curso');
        }
        var dat = {"codigo": codigo, "nombre": nombre, "creditos": creditos, "horasSemanales": horasSemanales};

        $.ajax({
            type: "PUT",
            url: "api/curso/",
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(dat),
            success: function (result) {
                console.log("Estado exitoso del servidor")
                if (result == "Exitoso") {
                   
                        alert( 'Curso se agrego correctamente');
                   
                } else {
                    
                      
                        alert('No se puedo realizar el mantenimiento');
                 
                }
            },
            error: function (error) {
                console.log("No se puedo recopilar los datos")
                console.log(error)
               alert('No se puedo realizar el mantenimiento');
            }
        });


    }
    
    
//    
//        $("#Boton_Buscar").on("click", function () {
//        BotonBuscarCurso();
//    });
//    
//    function BotonBuscarCurso() {
//        console.log("Entro a BotonBuscarCurso");
//        var codigo = $("#Text_Codigo").val();
//        var nombre = $("#Text_Nombre").val();
//        var creditos = $("#Text_Creditos").val();
//        var horasSemanales = $("#Text_HorasSemanales").val();
//        console.log(codigo);
//        console.log(nombre);
//        console.log(creditos);
//        console.log(horasSemanales);
//        if (codigo == '' || nombre == '' || creditos == '' || horasSemanales == '') {
//         alert('Llene todos los espacios antes de agregar un curso');
//        }
//        var dat = {"codigo": codigo, "nombre": nombre, "creditos": creditos, "horasSemanales": horasSemanales};
//
//        $.ajax({
//            type: "GET",
//            url: "api/curso/{codigo_cur}",
//            contentType: 'application/json',
//            async: false,
//            data: JSON.stringify(dat),
//            success: function (result) {
//                console.log("Estado exitoso del servidor")
//                if (result == "Exitoso") {
//                   
//                        alert( 'Curso se agrego correctamente');
//                   
//                } else {
//                    
//                      
//                        alert('No se puedo realizar el mantenimiento');
//                 
//                }
//            },
//            error: function (error) {
//                console.log("No se puedo recopilar los datos")
//                console.log(error)
//               alert('No se puedo realizar el mantenimiento');
//            }
//        });
//
//
//    }
//    
    
    