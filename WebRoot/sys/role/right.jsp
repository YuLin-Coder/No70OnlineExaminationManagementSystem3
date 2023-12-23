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
		<title>角色权限分配</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/theme.css">
		<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
		<script>
		function selectparent(obj){
			var ma = $(obj).attr("ma");
			
			var objs = $("[parent='"+ma+"']");
			if($(obj).attr("checked")=="checked"){
				for(var i=0;i<objs.length;i++){
					$(objs[i]).attr("checked","checked");
				}
			}else{
				for(var i=0;i<objs.length;i++){
					$(objs[i]).removeAttr("checked");
				}
			}
		}
		
		function selectchild(obj){
			var parent = $(obj).attr("parent");
			var objs = $("[ma='"+parent+"']");
			if($(obj).attr("checked")=="checked"){
				objs.attr("checked","checked");
			}else{
				var children =  $("[parent='"+parent+"']");
				var bl = false;
				for(var i=0;i<children.length;i++){
					if($(children[i]).attr("checked")=="checked"){
						bl = true;
						break;
					}
				}
				if(!bl){
					objs.removeAttr("checked");
				}
			}
		}
		
		</script>
	</head>

	<body class="content1">
		<script>
    		$('#a_leader_txt', parent.document).html('角色权限分配(${role.rolename})');
		</script>
		

		<div class="container-fluid">
			<div class="row-fluid">
				<form method="post" action="<%=basePath%>sys/role?cmd=saveright">
					<div class="btn-toolbar">
						<input type="submit" class="btn btn-primary" value="保存 ">
						<a href="<%=basePath%>sys/role?cmd=list" class="btn">取消</a>
						<input type="hidden" name="roleid" value="${role.roleid}"/>
						<div class="btn-group">
						</div>
					</div>
					<div class="well">
						<c:forEach items="${list}" var="fun">
							<c:if test="${fun.funpid==\"-1\"}">
								<div class="toc">
									<h3>
										<input type="checkbox" onclick="selectparent(this)" ma="${fun.funid}" name="ckrr" value="${fun.funid}" 
										<c:if test="${fun.rr==\"1\"}">
											checked="checked"
										</c:if>
										/>
										${fun.funname}
									</h3>
									<ul>
									<c:forEach items="${list}" var="child">
										<c:if test="${child.funpid==fun.funid}">
										<li>
											<input type="checkbox" onclick="selectchild(this)" parent="${fun.funid}" name="ckrr" value="${child.funid}" 
											<c:if test="${child.rr==\"1\"}">
											checked="checked"
											</c:if>
											/>
											${child.funname}
										</li>
										</c:if>
									</c:forEach>		
									</ul>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
