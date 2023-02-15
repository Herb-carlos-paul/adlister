<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Result</title>
    <link href="/css/redirect.css" rel="stylesheet">
</head>
<body>
<% if (request.getAttribute("message") != null) { %>
<h1><%= request.getAttribute("message") %></h1>
<% } %>
<h3>Redirecting you to profile... Item not found...</h3>




<div class="screen"></div>
<ul class="dance-animation">
    <li class="dance-frame dance-animation--dancer1"></li>
    <li class="dance-frame dance-animation--dancer2"></li>
    <li class="dance-frame dance-animation--dancer3"></li>
    <li class="dance-frame dance-animation--dancer4"></li>
    <li class="dance-frame dance-animation--dancer5"></li>
    <li class="dance-frame dance-animation--dancer6"></li>
    <li class="dance-frame dance-animation--dancer7"></li>
    <li class="dance-frame dance-animation--dancer8"></li>
    <li class="dance-frame dance-animation--dancer9"></li>
    <li class="dance-frame dance-animation--dancer10"></li>
    <li class="dance-frame dance-animation--dancer11"></li>
</ul>

<div class="play-music">
    <div id="music-animation" class="music-animation">
        <span class="bar bar1"></span>
        <span class="bar bar2"></span>
        <span class="bar bar3"></span>
        <span class="bar bar4"></span>
        <span class="bar bar5"></span>
    </div>
</div>

<script src="/js/redirect.js"></script>
</body>
</html>
