<%--
  Created by IntelliJ IDEA.
  User: Barto
  Date: 07.06.2021
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<html>
<body>
<ul>
    <c:forEach var="member" items="${memberList}" varStatus="status">
        <li>
            <a href="member.do?id=${status.index}">
                <c:out value="${member}"></c:out>
            </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>

<%--
<ul>
    <c:forEach var="member" items="${memberList}" varStatus="status">
        <li>
            <a href="member.do?id=${status.index}">
                <c:out value="${member}"></c:out>
            </a>
        </li>
    </c:forEach>
</ul>
--%>
