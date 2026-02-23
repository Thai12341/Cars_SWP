<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Dòng xe</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body style="background:#f8f9fa;">
    <%@ include file="includes/navbar.jsp" %>
    <div class="container" style="margin-top:100px;">
        <div class="card p-4 shadow">
            <div class="d-flex justify-content-between mb-4">
                <h3>Danh Sách Dòng Xe</h3>
                <a href="car-models?action=add" class="btn btn-success">Thêm Mới</a>
            </div>
            <table class="table table-hover">
                <thead class="bg-primary text-white text-center">
                    <tr>
                        <th>ID</th><th>Hãng Xe</th><th>Tên Dòng Xe</th><th>Năm SX</th><th>Thao Tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${models}" var="m">
                        <tr class="text-center">
                            <td>#${m.modelId}</td>
                            <td><span class="badge badge-info">${m.brand.brandName}</span></td>
                            <td>${m.modelName}</td>
                            <td>${m.year}</td>
                            <td>
                                <a href="car-models?action=edit&id=${m.modelId}" class="btn btn-sm btn-warning">Sửa</a>
                                <a href="car-models?action=delete&id=${m.modelId}" class="btn btn-sm btn-danger" onclick="return confirm('Xóa xe này?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>