<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/admin.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/pintuer.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
    <title>学生登录</title>
</head>

<body>
    <div class="bg"></div>
    <div class="container">
        <div class="line bouncein">
            <div class="xs6 xm4 xs3-move xm4-move">
                <div style="height:80px;"></div>
                <div class="media media-y margin-big-bottom">
                </div>
                <form action="<%=basePath%>user?cmd=stulogin" method="post" class="login-form">
                    <div class="panel loginbox">
                        <div class="text-center margin-big padding-big-top">
                            <h1>在线考试系统</h1>
                            <div class="form-top-left">
                                <a data-type="student" href="javascript:void(0);" style="color: red;">学生登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;
                                <a data-type="admin" href="javascript:void(0);">管理员登录</a><br>
                                <br>
                                <p id="mes" style="color: red;">${msg}</p>
                            </div>
                        </div>
                        <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="text" class="input input-big" name="username" placeholder="登录账号" data-validate="required:请填写账号" />
                                    <span class="icon icon-user margin-small"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="field field-icon-right">
                                    <input type="password" class="input input-big" name="userpwd" placeholder="登录密码" data-validate="required:请填写密码" />
                                    <span class="icon icon-key margin-small"></span>
                                </div>
                            </div>
                        </div>
                        <div style="padding:30px;"><button type="submit" class="button button-block bg-main text-big input-big">登录</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="<%=basePath%>js/jquery.js"></script>
    <script src="<%=basePath%>js/bootstrap.js"></script>
    <script src="<%=basePath%>js/pintuer.js"></script>
    <script>
		var basePath = '<%=basePath%>'
		$('.form-top-left a').click(function () {
		    $('.form-top-left').children().removeAttr("style");
		    var type = $(this).css('color', 'red').attr("data-type");
		    if (type == 'student') {
		        $(document).attr("title", "学生登录");
		        $("#mes").html('');
		        $(".login-form").attr("action", basePath + "user?cmd=stulogin");
		    }else {
		        $(document).attr("title", "管理员登录");
		        $("#register").html('');
		        $("#mes").html('');
		        $(".login-form").attr("action", basePath + "sys/user?cmd=login");
		    }
		})
	</script>
</body>

</html>