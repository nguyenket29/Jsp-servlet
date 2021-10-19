<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <h2>Nhập thông tin để đăng kí </h2>
        <form class="form-horizontal" action="<c:url value="/dang-ky"/>" method="post">
            <div class="form-group">
                <label class="control-label col-sm-2" for="userName">Tài khoản:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="userName" name="userName" placeholder="ví dụ: abcd0107">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="password">Mật khẩu:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Nhập mật khẩu ...">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Trạng thái:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="status" name="status">
                        <option>Chọn loại quyền</option>
                        <c:forEach items="${USER.status}" var="item">
                            <option>${item.status}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2">Phân Quyền:</label>
                <div class="col-sm-10">
                    <select class="form-control" id="roleId" name="roleId">
                        <option>Chọn loại quyền</option>
                        <c:forEach items="${USER.roleId}" var="item">
                            <option>${item.roleId}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <input type="hidden" value="resgister" name="action"/>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-success">Đăng ký</button>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
