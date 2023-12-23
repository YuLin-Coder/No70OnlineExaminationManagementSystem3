<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>">

		<title>增加系统功能</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	</head>

	<body class="content1">
		<script>
    		$('#a_leader_txt', parent.document).html('新建系统功能');
		</script>

		<div class="container-fluid">
			<div class="row-fluid">
				<form method="post" action="<%=basePath%>sys/fun?cmd=add">
					<div class="btn-toolbar">
						<input type="submit" class="btn btn-primary" value="保存 ">
						<a href="<%=basePath%>sys/fun?cmd=list" class="btn">取消</a>

					</div>

					<div class="well">
						<div class="tab-pane active in">
							<label>
								父功能名称：
							</label>
							<c:choose>
								<c:when test="${empty param.pid}">
									<input type="hidden" name="funpid" value="${funpid}" />
									<input type="text" name="funpname" value="${funpname}"
								readonly="readonly">			
								</c:when>
								<c:otherwise>
									<input type="hidden" name="funpid" value="${param.pid}" />
									<input type="text" name="funpname" value="${param.pname}"
								readonly="readonly">
								</c:otherwise>
							</c:choose>
							
							<label>
								功能名称：
							</label>
							<input type="text" name="funname" maxlength="10">
							<label>
								功能地址：
							</label>
							<input type="text" name="funurl" maxlength="100">
							<label>
								功能状态：
							</label>
							<select name="funstate">
								<option value="1">
									正常
								</option>
								<option value="0">
									锁定
								</option>
							</select>
							<div style="color: red">
								${msg}
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
