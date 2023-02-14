<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <link rel="stylesheet" type="text/css" href="../../css/ads-index.css" />
    <%--Style Buttons--%>
    <link href="../../css/card-buttons.css" rel="stylesheet" type="text/css">
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">I N V E N T O R Y</h1>
            <p class="text-center">Check out Our Deals!</p>
        </div>
    </div>

    <div class="container">
        <div class="align-forms">

            <%--Search by Title Form--%>
            <form action="/ads/title" method="get">
                <div class="form-group">
                    <input class="search-input-styling" id="title" name="title" type="text" placeholder="Search By Title">
                    <input type="submit" class="search-btn">
                </div>
            </form>

            <%--Search by Description Form--%>
            <form action="/ads/description" method="get">
                <div class="form-group">
                    <input class="search-input-styling" id="description" name="description" type="text" placeholder="Search By Description">
                    <input type="submit" class="search-btn">
                </div>
            </form>

            <%--Search by Categories Form with Checkboxes--%>
            <form action="/ads/categories" method="get">
                <div class="multiselect">
                    <div class="select-box" onclick="showCheckboxes()">
                        <select class="search-input-styling">
                            <option>Search By Category</option>
                        </select>
                        <div class="overSelect"></div>
                    </div>
                    <div id="checkboxes">
                        <c:forEach var="category" items="${categories}">
                            <label for="category-${category.getId()}">
                                <input type="checkbox" id="category-${category.getId()}" name="categories" value="${category.getId()}"/>${category.getName()}</label>
                        </c:forEach>
                        <input class="search-btn search-categories-btn" type="submit" value="Search By Categories" id="btn-position" />
                    </div>
                </div>
            </form>
        </div>

        <div class="card-container-div ad-cards-container">
            <%--Display Ads--%>
            <c:forEach var="ad" items="${ads}">
                <div class="card ad-card col-md-6">
                    <h3><strong><c:out value="${ad.title}"/></strong></h3>
                    <img src="<c:out value="${ad.imageUrl}"/>" alt=${ad.title} width="175" height="200">
                    <p><c:out value="${ad.description}"/></p>
                    <p><strong>Categories:</strong> <c:out value="${fn:join(categoriesDao.getCategoriesLinkedWithAd(ad.id).toArray(), ',')}"/></p>
                    <a href="/ads/ad?id=${ad.id}" class="btn btn-primary">View Ad</a>
                </div>
            </c:forEach>

            <%--Display Ads by Title--%>
            <c:forEach var="ad" items="${title}">
                <div class="card ad-card col-md-6">
                    <h3><strong><c:out value="${ad.title}"/></strong></h3>
                    <img src="<c:out value="${ad.imageUrl}"/>" alt=${ad.title} width="175" height="200">
                    <p><c:out value="${ad.description}"/></p>
                    <p><strong>Categories:</strong> <c:out value="${fn:join(categoriesDao.getCategoriesLinkedWithAd(ad.id).toArray(), ',')}"/></p>
                    <a href="/ads/ad?id=${ad.id}" class="btn btn-primary">View Ad</a>
                </div>
            </c:forEach>

            <%--Display Ads by Description--%>
            <c:forEach var="ad" items="${description}">
                <div class="card ad-card col-md-6">
                    <h3><strong><c:out value="${ad.title}"/></strong></h3>
                    <img src="<c:out value="${ad.imageUrl}"/>" alt=${ad.title} width="175" height="200">
                    <p><c:out value="${ad.description}"/></p>
                    <p><strong>Categories:</strong> <c:out value="${fn:join(categoriesDao.getCategoriesLinkedWithAd(ad.id).toArray(), ',')}"/></p>
                    <a href="/ads/ad?id=${ad.id}" class="btn btn-primary">View Ad</a>
                </div>
            </c:forEach>
        </div>

    </div>


    <%--JavaScript--%>
    <jsp:include page="/WEB-INF/partials/footer.jsp" />
    <script type="text/javascript" src="../../js/ads-index.js" ></script>
</body>
</html>
