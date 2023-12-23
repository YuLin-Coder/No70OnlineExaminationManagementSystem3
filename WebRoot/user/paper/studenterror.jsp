<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <title>在线答题</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/paper.css">
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/bootstrap.js"></script>
</head>

<body>
    <nav class="navbar navbar-default" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">在线考试系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<%=basePath%>user?cmd=paperlist">试题列表</a></li>
                <li class="active"><a href="<%=basePath%>user/studentPaper?cmd=stupaper">查看错题</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
					<c:when test="${userid!=null}">
						<li><a><c:out  value="${sessionScope.user.usertruename}"/></a></li>
						<li>
		                    <a href="<%=basePath%>sys/user?cmd=logout">注销</a>
		                </li>
					</c:when>
					<c:otherwise>
						<li><a href="login.jsp">登录</a></li>
					</c:otherwise>
				</c:choose>
                
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>

    <main class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h3 class="panel-title">
                    <c:out value="${pname}"></c:out>
                </h3>
            </div>
            <div class="panel-body">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#Radio">错题库</a>
                        </h4>
                    </div>
                    <div id="Radio" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <ol>
                                <c:forEach items="${pager.list}" var="item">
                                    <div class="subject" data-sid="${item.sid}" data-key="${item.skey}" data-skey="${item.studentkey}">
                                        <li> ${item.scontent}</li>
                                        <ol>
                                            <li><label data-value="A">${item.sa}</label></li>
                                            <li><label data-value="B">${item.sb}</label></li>
                                            <li><label data-value="C">${item.sc}</label></li>
                                            <li><label data-value="D">${item.sd}</label></li>
                                        </ol>
                                    </div>
                                </c:forEach>
                            </ol>
                            <div>
                                <ul class="pagination pagination-right">
                                    <li>
                                        <a>共计：${pager.pagectrl.pagecount}页/${pager.pagectrl.rscount}条记录</a>
                                    </li>
                                    <li>
                                        <c:if test="${pager.pagectrl.currentindex==1}" var="fp">
                                            <a style="disabled:true">上一页</a>
                                        </c:if>
                                        <c:if test="${!fp}">
                                            <a href="<%=basePath%>user/studentPaper?cmd=list&index=${pager.pagectrl.currentindex-1}">上一页</a>
                                        </c:if>
                                    </li>
                                    <c:forEach begin="${pager.pagectrl.minpage}" step="1" end="${pager.pagectrl.maxpage}" var="index">
                                        <li>
                                            <c:if test="${pager.pagectrl.currentindex==index}" var="t">
                                                <a style="color:red;background-color:#bbb">${index}</a>
                                            </c:if>
                                            <c:if test="${!t}">
                                                <a href="<%=basePath%>user/studentPaper?cmd=list&index=${index}">${index}</a>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <c:if test="${pager.pagectrl.currentindex==pager.pagectrl.pagecount}" var="fp">
                                            <a style="disabled:true">下一页</a>
                                        </c:if>
                                        <c:if test="${!fp}">
                                            <a href="<%=basePath%>user/studentPaper?cmd=list&index=${pager.pagectrl.currentindex+1}">下一页</a>
                                        </c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </main>
    <script>
    // 获取basePath 
    var basePath = '<%=basePath%>'
    $(function(ev) {
        var len = $('.subject').length;
        $('.subject').each(function(index){
        var i = index
        var self = $(this)
        
        self.find('label').each(function(){
        	var label = $(this)
        	if(self.data('key')==label.data('value')){
        		label.parent().addClass('correct')
        	}
        	if(self.data('skey')==label.data('value')){
        		label.parent().addClass('error')
        	}
        })
        
        //var data = {userid:userid,sid:self.data('sid'),studentkey:self.data('skey'),studentstate:self.data('state'),pname:pname}
        })

    })
    </script>
</body>

</html>