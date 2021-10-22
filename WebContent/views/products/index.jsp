<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../blocks/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../blocks/head.jsp"%>
</head>
<body>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>ID</th>
				<th>NAME</th>
				<th>PRICE</th>
				<th>AMOUNT</th>
			</tr>
		</thead>
		<tbody>
			<a:choose>
				<a:when test="${requestScope.listProduct.size() > 0}">
					<a:forEach items="${requestScope.listProduct}" var="product">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.price}</td>
							<td>${product.amount}</td>
						</tr>
					</a:forEach>
				</a:when>
				<a:otherwise>
					<tr>
						<td colspan="4"><h1 class="text-center">EMPTY</h1></td>
					</tr>
				</a:otherwise>
			</a:choose>
		</tbody>
	</table>
</body>
</html>