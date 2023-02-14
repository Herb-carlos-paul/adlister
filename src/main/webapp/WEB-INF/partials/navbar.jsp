<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="/css/navbar.css" rel="stylesheet" type="text/css">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand navbar-brand-link nav-link" href="/ads">A D L I S T E R</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${sessionScope.user == null}" >
                    <li class="nav-item"><a class="nav-link sub-nav-link" href="/register">Register</a></li>
                    <li class="nav-item"><a class="nav-link sub-nav-link" href="/login">Login</a></li>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${sessionScope.user != null}" >
                    <li class="nav-item"><a class="nav-link " href="/profile">Profile</a></li>
                    <li class="nav-item"><a class="nav-link" href="/ads">Ads</a></li>
                    <li class="nav-item"><a class="nav-link" href="/ads/create">Create Ad</a></li>
                    <li class="nav-item"><a class="nav-link" href="/logout">Logout</a></li>
                </c:when>
            </c:choose>
        </ul>
    </div>
    </div>
</nav>