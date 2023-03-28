<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../../layouts/header.jsp" %>
<!-- C3 charts css -->
<link href="public/plugins/c3/c3.min.css" rel="stylesheet" type="text/css"/>´ñ{-.
<!-- DataTables -->
<link href="public/plugins/datatables/dataTables.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/datatables/buttons.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<!-- Responsive datatable examples -->
<link href="public/plugins/datatables/responsive.bootstrap4.min.css" rel="stylesheet"
      type="text/css"/>
<link href="public/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
      rel="stylesheet">
<%@ include file="../../layouts/headerStyle.jsp" %>
<body class="fixed-left">
<%@ include file="../../layouts/loader.jsp" %>
<!-- Begin page -->
<div id="wrapper">
    <%@ include file="../../layouts/navbar.jsp" %>
    <!-- Start right Content here -->
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <!-- Top Bar Start -->
            <%@ include file="../../layouts/toolbar.jsp" %>
            <!-- Top Bar End -->
            <!-- ==================
            PAGE CONTENT START
            ================== -->
            <div class="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                  <label class="control-label">Autor del libro</label>
                                    <select id="id_autor" name="id_autor" class="form-control select2">

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card m-b-20">
                                    <div class="card-body">
                                        <div id="aqui_tabla"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div><!-- container -->
            </div> <!-- Page content Wrapper -->
        </div> <!-- content -->
        <%@ include file="../../layouts/footer.jsp" %>
    </div>
    <!-- End Right content here -->

    <!-- END wrapper -->
    <%@ include file="../../layouts/footerScript.jsp" %>
    <!-- Peity chart JS -->
    <script src="public/plugins/peity-chart/jquery.peity.min.js"></script>
    <!--C3 Chart-->
    <script src="public/plugins/d3/d3.min.js"></script>
    <script src="public/plugins/c3/c3.min.js"></script>
    <!-- KNOB JS -->
    <script src="public/plugins/jquery-knob/excanvas.js"></script>
    <script src="public/plugins/jquery-knob/jquery.knob.js"></script>
    <!-- Page specific js -->
    <script src="public/assets/pages/dashboard.js"></script>
    <!-- App js -->
    <script src="public/assets/js/app.js"></script>
    <script src="public/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="public/plugins/datatables/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript" src="public/plugins/parsleyjs/parsley.min.js"></script>
    <script src="public/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <%@page import="java.time.LocalDateTime" %>
    <%@ page import="java.time.temporal.ChronoField" %>
    <script src="./modulos/vista_autor_libro/funcion_autor_libro.js"></script>
