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

		<title>角色管理</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	</head>

	<body class="content1">
		<div class="container-fluid">
			<div class="row-fluid">
				<form class="form-inline" method="post"
					action="<%=basePath%>sys/role?cmd=list">
					<input class="input-xlarge" placeholder="角色名称..." name="sname"
						type="text" value="${param.sname}">
					<input class="btn icon-search" type="submit" value="查询" />
					<a class="btn btn-primary"
						href="<%=basePath%>sys/role/add.jsp"> <i
						class="icon-plus"></i> 新增 </a>
				</form>

				<div class="well">
					<table class="table">
						<thead>
							<tr>
								<th>
									角色名称
								</th>
								<th>
									角色说明
								</th>
								<th>
									角色状态
								</th>
								<th style="width: 90px;">
									编辑
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pager.list}" var="item">
								<tr>
									<td>
										${item.rolename}
									</td>
									<td>
										${item.roledesc}
									</td>
									<td>
										<c:choose>
											<c:when test="${item.rolestate==\"1\"}">
												正常		
											</c:when>
											<c:otherwise>锁定</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="<%=basePath%>sys/role?cmd=toedit&id=${item.roleid}">编辑</a>
										&ensp;
										<a
											href="<%=basePath%>sys/role?cmd=initrole&roleid=${item.roleid}">权限</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination pagination-right">
						<ul>
							<li>
								<a>共计：${pager.pagectrl.pagecount}页/${pager.pagectrl.rscount}条记录</a>
							</li>

							<li>
								<c:if test="${pager.pagectrl.currentindex==1}" var="fp">
									<a style="disabled: true">上一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a
										href="<%=basePath%>sys/fun?cmd=list&index=${pager.pagectrl.currentindex-1}">上一页</a>
								</c:if>
							</li>

							<c:forEach begin="${pager.pagectrl.minpage}" step="1"
								end="${pager.pagectrl.maxpage}" var="index">
								<li>
									<c:if test="${pager.pagectrl.currentindex==index}" var="t">
										<a style="color: red; background-color: #bbb">${index}</a>
									</c:if>
									<c:if test="${!t}">
										<a href="<%=basePath%>sys/fun?cmd=list&index=${index}">${index}</a>
									</c:if>
								</li>
							</c:forEach>

							<li>
								<c:if
									test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}"
									var="fp">
									<a style="disabled: true">下一页</a>
								</c:if>
								<c:if test="${!fp}">
									<a
										href="<%=basePath%>sys/fun?cmd=list&index=${pager.pagectrl.currentindex+1}">下一页</a>
								</c:if>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
