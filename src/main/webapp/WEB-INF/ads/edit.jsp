<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Ad" />
    </jsp:include>
    <link href="/css/edit.css" rel="stylesheet" type="text/css">
    <link href="/css/navbar.css" rel="stylesheet" type="text/css">

</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <div class="new-ad">
            <form action="/ads/edit" method="post">

                <h1>Edit Ad</h1>

                <div>
                    <textarea id="title" name="title" class="create-box" type="text" placeholder="T I T L E" value="${ad.title}" required></textarea>
                </div>
                <div>
                    <textarea id="description" name="description" class="create-box" type="text" placeholder="D E S C R I P T I O N" value="${ad.description}" required></textarea>
                </div>
                <div>
                    <textarea id="image" name="image" class="create-box" type="text" placeholder="I M A G E - A D D R E S S" value="${ad.imageUrl}" required></textarea>
                </div>

                <input type="hidden" name="id" value="${ad.id}">
                <input type="submit" class="btn">
            </form>
        </div>


    </div>

    <%--JavaScript--%>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
</body>
</html>
