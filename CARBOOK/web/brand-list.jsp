<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản Lý Hãng Xe - CarBook</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        /* CSS Fix lỗi đè chữ và làm bảng đẹp hơn */
        body { background-color: #f8f9fa; }
        .main-container { 
            margin-top: 120px; /* Đẩy xuống để không bị Navbar đè */
            background: #ffffff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .header-title {
            color: #01d28e; /* Màu xanh đặc trưng của CarBook */
            font-weight: 700;
            margin-bottom: 30px;
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
        }
        .brand-logo-img {
            width: 60px;
            height: auto;
            border-radius: 5px;
        }
        .btn-custom {
            padding: 5px 15px;
            border-radius: 5px;
            font-size: 14px;
        }
    </style>
</head>
<body>
    <%@ include file="includes/navbar.jsp" %>

    <div class="container main-container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="header-title">DANH SÁCH HÃNG XE</h2>
            <c:if test="${sessionScope.user.roleId == 1 || sessionScope.user.roleId == 2}">
                <a href="brand?action=add" class="btn btn-success">
                    <i class="ion-ios-add"></i> Thêm Hãng Mới
                </a>
            </c:if>
        </div>

        <table class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Logo</th>
                    <th>Tên hãng</th>
                    <th>Quốc gia</th>
                    <th>Ngày tạo</th>
                    <th class="text-center">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${brands}" var="b">
                    <tr>
                        <td class="align-middle">${b.brandId}</td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${not empty b.logoURL}">
                                    <img src="${b.logoURL}" class="brand-logo-img" alt="logo">
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-light">No Image</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle"><strong>${b.brandName}</strong></td>
                        <td class="align-middle">${b.country}</td>
                        <td class="align-middle">
                            <fmt:formatDate value="${b.createdAt}" pattern="dd/MM/yyyy"/>
                        </td>
                        <td class="align-middle text-center">
                            <a href="brand?action=edit&id=${b.brandId}" class="btn btn-warning btn-custom">
                                <i class="ion-ios-create"></i> Sửa
                            </a>
                            <button onclick="confirmDelete(${b.brandId}, '${b.brandName}')" class="btn btn-danger btn-custom">
                                <i class="ion-ios-trash"></i> Xóa
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script>
        function confirmDelete(id, name) {
            if (confirm('Bạn có chắc chắn muốn xóa hãng xe ' + name + ' không?')) {
                window.location.href = 'brand?action=delete&id=' + id;
            }
        }
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>