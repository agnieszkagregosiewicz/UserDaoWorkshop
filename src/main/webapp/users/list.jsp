<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UserDAO - Dashboard</title>

    <!-- Custom fonts for this template-->
    <link href="../theme/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../theme/css/sb-admin-2.min.css" rel="stylesheet" type="text/css" />

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/user/list">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">User <sup>CRUD</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="/user/list">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Dashboard</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider">

        <!-- Heading -->
        <%--            <div class="sidebar-heading">--%>
        <%--                Interface--%>
        <%--            </div>--%>

        <%--                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">--%>
        <%--                    <div class="bg-white py-2 collapse-inner rounded">--%>
        <%--                        <h6 class="collapse-header">Login Screens:</h6>--%>
        <%--                        <a class="collapse-item" href="login.html">Login</a>--%>
        <%--                        <a class="collapse-item" href="register.html">Register</a>--%>
        <%--                        <a class="collapse-item" href="forgot-password.html">Forgot Password</a>--%>
        <%--                        <div class="collapse-divider"></div>--%>
        <%--                        <h6 class="collapse-header">Other Pages:</h6>--%>
        <%--                        <a class="collapse-item" href="404.html">404 Page</a>--%>
        <%--                        <a class="collapse-item" href="blank.html">Blank Page</a>--%>
        <%--                    </div>--%>
        <%--                </div>--%>


        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

        <!-- Sidebar Message -->


    </ul>
    <!-- End of Sidebar -->


    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">


        <!-- Main Content -->
        <div id="content">
            <!-- Topbar -->

            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                <!-- Sidebar Toggle (Topbar) -->

                    <c:if test="${sessionScope.message != null}">
                            <div class="alert alert-primary alert-dismissible fade show" role="alert">
                            ${sessionScope.message}
                            </div>
                    </c:if>
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>
            </nav>
            <!-- End of Topbar -->


            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">User CRUD</h1>
                    <a href="add" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                            class="fas fa-download fa-sm text-white-50"></i> Dodaj Użytkownika</a>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Akcja</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Akcja</th>
                            </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="item" items="${users}">
                                <tr>
                                    <td>${item.id}</td>
                                    <td>${item.name}</td>
                                    <td>${item.email}</td>
                                    <td><a href = "remove?id=${item.id}">Usuń</a>
                                        <a href = "edit?id=${item.id}">Edit</a>
                                        <a href = "show?id=${item.id}">Pokaż</a></td>
                                </tr>
                                </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>

                <!-- Content Row -->
                <div class="row">
                </div>

                <div class="col-lg-6 mb-4">
                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <%@ include file="/footer.jsp" %>
                <!-- End of Footer -->

            </div>
            <!-- End of Page Content -->
        </div>
        <!-- End of Main Content -->
    </div>
    <!-- End of Content Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
</div>
<!-- End of Page Wrapper -->
</body>

</html>