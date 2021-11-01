<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="css" fragment="true"%>
<%@attribute name="js" fragment="true"%>
<%@attribute name="content" fragment="true"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../blocks/head.jsp"%>
<jsp:invoke fragment="css" />
</style>
</head>
<body>
	<div class="wrapper">
		<!-- start sidebar -->
		<%@ include file="../blocks/admin/sidebar.jsp"%>
		<!-- end sidebar -->

		<!-- start main -->
		<div class="active" id="body">
			<!-- start navbar -->
			<%@ include file="../blocks/admin/navbar.jsp"%>
			<!-- end navbar -->

			<!-- start content -->
			<div class="content">
				<div class="container-fluid">
					<div class="page-title">
						<jsp:invoke fragment="content"/>
					</div>
				</div>
			</div>
			<!-- end content -->
		</div>
		<!-- end main -->
	</div>
	<%@ include file="../blocks/script.jsp"%>
	<jsp:invoke fragment="js" />
</body>
</html>