<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
    <link href="../css/login.css" rel="stylesheet" type="text/css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<video src="img/electronic.mp4" width="100"  loop muted autoplay></video>

    <header>
        <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    </header>
    <div class="container">
        <div class="login">
            <h2>PLEASE LOG IN</h2>
            <form action="/login" method="POST">
                <div >
                    <input id="username" name="username" class="login-box-design" type="text" placeholder="U S E R N A M E" required>
                </div>
                <div>
                    <input id="password" name="password" class="login-box-design" type="password" placeholder="P A S S W O R D" required>
                </div>
                <input type="submit" class="login-btn" value="LOG IN">
            </form>
        </div>
    </div>

<div class="bg-video-wrap">
    <video src="img/sign-up.mp4" width="100"  loop muted autoplay></video>
</div>


    <%--JavaScript--%>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>--%>
</body>
</html>
