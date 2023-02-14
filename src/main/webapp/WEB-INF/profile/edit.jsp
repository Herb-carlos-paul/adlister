
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Profile"/>
    </jsp:include>
    <link href="/css/profile-edit.css" rel="stylesheet" type="text/css">
</head>
<body>
<main class="w-100 h-100 btn-primary">
    <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    <div class="container">
        <div class="edit-profile">
            <h1>Please update your information.</h1>
            <form action="/profile/edit" method="post">
                <div>
                    <input id="username" name="username" class="create-box" type="text" placeholder="U S E R N A M E"
                           required>
                </div>
                <div>
                    <input id="email" name="email" class="create-box" type="text" placeholder="E M A I L" required>
                </div>
                <div>
                    <input id="passwoord" name="password" class="create-box" type="text" placeholder="P A S S W O R D"
                           required>
                </div>
                <input id="id" type="hidden" name="id" value="${sessionScope.user.id}">
                <input type="submit" class="btn">
            </form>
        </div>
    </div>

    <jsp:include page="/WEB-INF/partials/footer.jsp" />

</main>

</body>
</html>