<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>	
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Home</title>
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css"/>
    <!-- Slick -->
    <link type="text/css" rel="stylesheet" href="/css/slick.css"/>
    <link type="text/css" rel="stylesheet" href="/css/slick-theme.css"/>
    <!-- nouislider -->
    <link type="text/css" rel="stylesheet" href="/css/nouislider.min.css"/>
    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="/css/style.css"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>


<!-- HEADER -->
<jsp:include page="common/header.jsp" />

<hr/>

<!--SIGN UP-->
<!-- row -->
<div class="row">
    <!-- space -->
    <div class="col-md-1"></div>
    <!-- login -->
    <div class="col-md-4">

    </div>

    <!-- form -->
    <div class="col-md-3">
        <form class="form-horizontal" action="/user/register">
            <h3 class="sso-title">注册</h3>
            <hr/>
            <div class="control-group">
                <div class="controls">
                    <input name="username" type="text" placeholder="用户名">
                </div>
            </div>
            <br/>
            <div class="control-group">
                <div class="controls">
                    <input name="password" type="password" placeholder="密码">
                </div>
            </div>
            <br/>
            <div class="control-group">
                <div class="controls">
                    <input name="confirmPassword" type="password" placeholder="确认密码">
                </div>
            </div>
            <br/>
            <div class="control-group">
                <div class="controls">
                    <input name="phone" type="text" placeholder="手机号">
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <label class="checkbox">
                        <input type="checkbox"> Remember me
                    </label>
                    <hr/>
                    <button type="submit" class="btn btn-large btn-primary">Sign in</button>
                </div>
            </div>
        </form>
    </div>
    <!-- space -->
    <div class="col-md-4 clearfix"></div>
</div>
<!-- row -->

<hr/>

<!-- FOOTER -->
<jsp:include page="common/footer.jsp" />
<!-- /FOOTER -->

<!-- jQuery Plugins -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/slick.min.js"></script>
<script src="/js/nouislider.min.js"></script>
<script src="/js/jquery.zoom.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>