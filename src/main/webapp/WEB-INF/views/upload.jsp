<%@page pageEncoding="UTF-8" language="java" contentType="text/html; UTF-8" %>
<html>
<body>
<h2>上传文件</h2>
<form action="uploadFileHandler" method="post" enctype="multipart/form-data">
    <input type="file" name="uploadFile">
    <input type="submit" value="上传">
</form>
</body>
</html>
