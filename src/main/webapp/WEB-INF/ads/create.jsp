<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
    <link href="/css/create.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">

        <div class="new-ad">
            <h1>Create a new Ad</h1>
            <form action="/ads/create" method="post">
                <div>
                    <input id="title" name="title" class="create-box" type="text" placeholder="T I T L E" required>
                </div>
                <div>
                    <textarea id="description" name="description" class="create-box" type="text" placeholder="D E S C R I P T I O N" required></textarea>
                </div>
                <div>
                    <input id="image" name="image" class="create-box" type="text" placeholder="I M A G E - A D D R E S S">
                </div>
                <h2>Select A Category</h2>
                <div class="select-boxes">
                    <c:forEach var="category" items="${categories}">
                        <input id="category-${category.getId()}" name="category" class="checkbox" value="${category.getId()}" type="checkbox">
                        <label for="category-${category.getId()}">${category.getName()}</label>
                        <br>
                    </c:forEach>
                </div>
                <input type="submit" class="btn">
            </form>
        </div>
    </div>


    <jsp:include page="/WEB-INF/partials/footer.jsp" />
</body>
</html>
