<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>异常页面</title>
</head>
<body>
<table>
    <tr>
        <td>
            <img src="${pageContext.request.contextPath}/images/404.jpeg">
        </td>
    </tr>
    <tr>
        <td align="center">
            <p><h1>${error}</h1></p>
        </td>
    </tr>
</table>
</body>
</html>
