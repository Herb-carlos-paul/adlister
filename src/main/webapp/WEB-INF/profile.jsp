<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
    <link href="../css/ads-index.css" rel="stylesheet" type="text/css">
    <%--Style Buttons--%>
    <link href="../css/card-buttons.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar-profile.jsp" />

    <div class="container">
        <h1>Welcome, ${sessionScope.user.username.substring(0,1).toUpperCase()}${sessionScope.user.username.substring(1).toLowerCase()}!</h1>
    </div>

    <div>
        <h2>Here are all of your posts</h2>
        <div class="card-container">
                <c:forEach var="ad" items="${ads}">
                    <div class="ad-card card">
                        <h3><strong><c:out value="${ad.title}"/></strong></h3>
                        <img src="<c:out value="${ad.imageUrl}"/>" alt=${ad.title} width="175" height="200">
                        <p><c:out value="${ad.description}"/></p>
                        <p><strong>Categories:</strong> <c:out value="${fn:join(categoriesDao.getCategoriesLinkedWithAd(ad.id).toArray(), ',')}"/></p>
                        <a class="btn delete-btn deletebtn" id="deletebtn" href="/ads/delete?id=${ad.id}">Delete</a>
                        <a class="btn btn edit-btn editbtn" id="editbtn" href="/ads/edit?id=${ad.id}">Edit</a>
                    </div>
                </c:forEach>
        </div>
    </div>


    <%--JavaScript--%>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <script type="text/javascript" src="../js/ads-index.js" ></script>
</body>
</html>
