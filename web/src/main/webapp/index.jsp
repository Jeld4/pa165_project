<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>eShop AngularJS</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"  crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular-route.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/angular_app.js"></script>
</head>

<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Pneuservis</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#!/allUsers">All users</a></li>
                <li><a href="#!/allOrders">All orders</a></li>
                <li><a href="#!/allServices">All services</a></li>
                <li><a href="#!/userRegister">Register</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div ng-app="pneuApp"><!-- AngularJS takes care of this element -->

        <!-- Bootstrap-styled alerts, visible when $rootScope.xxxAlert is defined -->
        <div ng-show="warningAlert" class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideWarningAlert()"> <span aria-hidden="true">&times;</span></button>
            <strong>Warning!</strong> <span>{{warningAlert}}</span>
        </div>
        <div ng-show="errorAlert" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideErrorAlert()"> <span aria-hidden="true">&times;</span></button>
            <strong>Error!</strong> <span>{{errorAlert}}</span>
        </div>
        <div ng-show="successAlert" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideSuccessAlert()"> <span aria-hidden="true">&times;</span></button>
            <strong>Success !</strong> <span>{{successAlert}}</span>
        </div>

        <!-- the place where HTML templates are replaced by AngularJS routing -->
        <div ng-view></div>
    </div>

</div>
</body>
</html>
