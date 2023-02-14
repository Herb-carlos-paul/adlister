<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to ELECTRO!" />
    </jsp:include>
    <link href="css/landing-page.css" rel="stylesheet" type="text/css">
    <link rel="icon" href="img/icon.png">
</head>
<body>
    <div class="container">
        <jsp:include page="/WEB-INF/partials/landing-page-navbar.jsp" />
        <h1 id="heading">E L E C T R O </h1>
        <h4 id="sub-heading">Electronic Listings</h4>
    </div>

    <div class="overlay"></div>

    <div class="bg-video-wrap">
        <video src="img/electronic.mp4" width="100"  loop muted autoplay></video>
    </div>
</body>
</html>
