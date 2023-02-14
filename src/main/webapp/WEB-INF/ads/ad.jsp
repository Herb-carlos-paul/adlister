<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${ad.title}" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h3><strong><c:out value="${ad.title}"/></strong></h3>
        <h2><c:out value="Posted by: ${usersDao.findById(ad.userId).getUsername().substring(0, 1).toUpperCase()}${usersDao.findById(ad.userId).getUsername().substring(1).toLowerCase()}"/></h2>
        <p><c:out value="Description: ${ad.description}"/></p>
        <p><strong>Categories:</strong> <c:out value="${fn:join(categoriesDao.getCategoriesLinkedWithAd(ad.id).toArray(), ',')}"/></p>
        <img src="<c:out value="${ad.imageUrl}"/>" alt=${ad.title} width="300" height="350">
    </div>


    <%--JavaScript--%>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
</body>
</html>