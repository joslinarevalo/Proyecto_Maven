$(function(){
    console.log("Jquery esta funciona");
    cargar_datos();
    $(document).on("change","#id_autor",function (e){
        console.log("el value de id autor es: ",$("#id_autor").val());
       obtener_libros($("#id_autor").val());
    });
});
function obtener_libros(id_autor) {
    mostrar_cargando("Espere", "Consultando Libros y Autores")
    var datos = {"consultar_datos":"si_consulta_autoreslibros_especifico","id_autor":id_autor};
    console.log("datos enviados: ", datos);
    $.ajax({
        dataType:"json",
        method:"POST",
        url:"Mostrar_Autor_Libro",
        data:datos
    }).done(function (json){
        console.log("consultar libros: ", json);
        if (json[0].resultado == "exito"){
            $("#aqui_tabla").empty().html(json[0].tabla);
            $('#tabla_autores').DataTable();
        }else {
            Swal.fire('Error', 'No se pudo completar la petición, intentelo más tarde!', 'error');
        }
    }).fail(function (json){
        console.log("json",json);
    }).always(function (){
    })
}
function mostrar_cargando(titulo, mensaje =""){
    Swal.fire({
        title: titulo,
        html: mensaje,
        timer: 2000,
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        },
        willClose: () => {
        }
    }).then((result) => {
        if (result.dismiss === Swal.DismissReason.timer) {
            console.log('no se cargan datos ')
        }
    })
}
function cargar_datos(estado = 1) {
    mostrar_cargando("Procesando solicitud", "Espere mientras se obtiene la información solicitada")
    var datos = {"consultar_datos":"si_consulta_autoresconlibros"};
    console.log("datos enviados: ", datos);
    console.log("entro a cargar_datos ");
    $.ajax({
        dataType:"json",
        method:"POST",
        url:"Mostrar_Autor_Libro",
        data:datos
    }).done(function (json){
        Swal.close();
        console.log("datos consultados1: ", json);
        if (json[0].resultado == "exito"){
            $("#id_autor").empty().html(json[0].opciones);
            //buscar donde va opciones
        }else {
            Swal.fire('Error', 'No se pudo completar la petición, intentelo más tarde!', 'error');
        }
    }).fail(function (){
    }).always(function (){
    })
}