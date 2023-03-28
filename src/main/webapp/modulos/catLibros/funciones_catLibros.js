$(function (){
    var fecha = new Date();
    console.log("Jquery esta funcionando");
    cargar_datos();
    $(document).on("click", "#registrar_categoria", function (e){
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_categoria").modal("show");
    });
    $(document).on("submit", "#formulario_registro", function (e){
        e.preventDefault();
        mostrar_cargando("Procesando solicitud", "Espere mientras se almacenan los datos")
        var datos = $("#formulario_registro").serialize();
        console.log("formulario: ", datos);
        $.ajax({
            dataType:"json",
            method:"POST",
            url:"RegCatLib",
            data:datos
        }).done(function (json){
            Swal.close();
            console.log("datos insertados: ", json);
            if (json[0].resultado == "exito"){
                Swal.fire({
                    icon: 'success',
                    title: 'Categoria',
                    text: 'Registrada con éxito!',
                    confirmButtonText: "Ok",
                }).then((confirmacion) => {
                    if (confirmacion) {
                        cargar_datos();
                    } else
                        ;
                });
                $("#md_registrar_categoria").modal("hide");
            }else {
                Swal.fire('Accion no completada', 'No pudo registrarse la categoria', 'error')
            }
        }).fail(function (){
        }).always(function (){
        })
    });
    $(document).on("click",".btn_eliminar",function (e){
        e.preventDefault();
        Swal.fire({
            title:'Desea elimiar el registro?',
            text:'Al continuar con esta accion no podra ser revertida y los datos sera borrados completamente',
            showDenyButton: true,
            showCancelButton: false,
            confirmButtonText:'Si',
            denyButtonText:'No',
        }).then((result)=>{
            if(result.isConfirmed){
                eliminar($(this).attr('data-id'));
            }else if (result.isDenied){
                Swal.fire('Accion cancelada por el usuario','info')
            }
        })
    });

    $(document).on("click",".btn_actualizar", function (e) {
        e.preventDefault();
        mostrar_cargando("Espere","Obteniendo datos");
        var id = $(this).attr("data-id");
        console.log("El id a editar es: ",id);
        var datos={"consultar_datos":"si_concategoria_especifica","id": id}
        $.ajax({
            dataType: "json",
            method: "POST",
            url:'RegCatLib',
            data : datos,
        }).done(function (json){
            console.log("El consultar especifico", json);
            if(json[0].resultado=="exito"){
               // console.log("codigoCategoria:",json[2]['codigocategoria']);
                $("#formulario_registro").trigger("reset");
                $('#llave_persona').val(json[0].id);
                $('#consultar_datos').val("si_actualizalo");
                $('#codigoCategoria').val(json[0].codigoCategoria);
                $('#nombreCategoria').val(json[0].nombreCategoria);
                $('#md_registrar_categoria').modal('show');
            }
        }).fail(function (){

        }).always(function (){

        });
    });
    $(document).on("click","#registrar_categoria",function (e){
        e.preventDefault();
        $("#formulario_registro").trigger("reset");
        $("#md_registrar_categoria").modal("show");
    });
});

function eliminar(id){
 mostrar_cargando("Procesando solicitud","Espere mientras se eliminan los datos")
    var datos= {"consultar_datos":"si_eliminalo","id":id };
 console.log("entra al fuction eliminar id="+id);
 console.log("fuction eliminar (id){"+datos);
 $.ajax({
     dataType:"json",
     method:"POST",
     url:"RegCatLib",
     data:datos
 }).done(function (json){
     Swal.close();
     console.log("datos consultados:",json);
     if (json[0].resultado=="exito"){
         Swal.fire(
             'Excelente',
             'El dato fue eliminado',
             'success'
         );
         cargar_datos();
     }else{
         Swal.fire(
             'Error',
             'No se pudo eliminar el dato intentolo más tarde',
             'error'
         );
     }
 }).fail(function (){

 }).always(function (){

 });
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
            console.log('I was closed by the timer')
        }
    })
}

function cargar_datos(estado = 1) {
    mostrar_cargando("Procesando solicitud", "Espere mientras se obtiene la información solicitada")
    var datos = {"consultar_datos":"si_consulta_categorias", "estado":estado};
    console.log("datos enviados: ", datos);
    $.ajax({
        dataType:"json",
        method:"POST",
        url:"RegCatLib",
        data:datos
    }).done(function (json){
        Swal.close();
        console.log("datos consultados1: ", json);
        if (json[0].resultado == "exito"){
            $("#tablaCategorias").empty().html(json[0].tabla);
            $('#tabla_categorias').DataTable();
        }else {
            Swal.fire('Error', 'No se pudo completar la petición, intentelo más tarde!', 'error');
        }
    }).fail(function (){
    }).always(function (){
    })
}