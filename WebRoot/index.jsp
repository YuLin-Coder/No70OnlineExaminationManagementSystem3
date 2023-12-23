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
    <base target="main" />
    <title>欢迎使用在线考试管理系统</title>
    <link rel="stylesheet" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" href="<%=basePath%>css/admin.css">
    <script src="<%=basePath%>js/jquery.js">
        function av() {
            alert(1111)
        }
    </script>

</head>

<body style="background-color:#f2f9fd;">
    <div class="header bg-main">
        <div class="logo margin-big-left fadein-top">
            <h1><img src="css/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
        </div>
        <div class="head-l">
            <a href="javascript:void(0);" class="button button-little bg-blue"><span class="icon-user"></span> ${user.usertruename}(${user.rolename})</a> &nbsp;&nbsp;
            <a class="button button-little bg-red" href="<%=basePath%>sys/user?cmd=logout" target="_self"><span class="icon-power-off"></span> 退出登录</a>
        </div>
    </div>
    <div class="leftnav">
        <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
        <c:forEach items="${list}" var="top">
            <c:if test="${top.funpid==\"-1\"}">
                <h2><span class="icon-briefcase"></span>${top.funname}</h2>
                <ul id="error-menu${top.funid}" class="nav nav-list collapse" style="display:block">
                    <c:forEach items="${list}" var="child">
                        <c:if test="${child.funpid==top.funid}">
                            <li>
                                <a href="<%=basePath%>${child.funurl}" target="right"><span class="icon-caret-right"></span>${child.funname}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </c:if>
        </c:forEach>
    </div>
    <script type="text/javascript">
        $(function(){
            $(".leftnav h2").click(function(){
                $(this).next().slideToggle(200);	
                $(this).toggleClass("on"); 
            })
            $(".leftnav ul li a").click(function(){
                $("#a_leader_txt").text($(this).text());
                $(".leftnav ul li a").removeClass("on");
                $(this).addClass("on");
            })
        });
    </script>
    <ul class="bread">
        <li><a onclick="av()" href="av()" target="right" class="icon-home"> 首页</a></li>
        <li><a href="javascript:void(0);" target="right" id="a_leader_txt">网站信息</a></li>
    </ul>
    <div class="admin">
        <iframe scrolling="auto" rameborder="0" src="" name="right" width="100%" height="100%"></iframe>
    </div>
</body>
</html>