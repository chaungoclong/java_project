<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="css" fragment="true"%>
<%@attribute name="js" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../blocks/head.jsp"%>
<link href="/PROJECT/assets/css/auth.css" rel="stylesheet">
<jsp:invoke fragment="css" />
</head>
<body>
	<div class="wrapper">
		<div class="auth-content">
			<jsp:invoke fragment="content"/>
		</div>
	</div>
	<%@ include file="../blocks/script.jsp"%>
	<jsp:invoke fragment="js" />
</body>
</html>