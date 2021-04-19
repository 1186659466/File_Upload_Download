<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>下载文件</title>
</head>
<body>
<c:forEach items="${requestScope.fileNames}" var="fn">
    <form action="downloadHandler" method="post">
            ${fn}<input type="hidden" name="fileName" value="${fn}">
                <input type="submit" value="下载">
    </form>
    <form action="loadOneFile" method="post">
        <input type="hidden" name="fileName" value="${fn}">
        <input type="submit" value="查看文件信息">
    </form>
</c:forEach>

<a href="upload">继续上传</a><br>
<a href="upload.html">返回主页面</a><br>
</body>
</html>
