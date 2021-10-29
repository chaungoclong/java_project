<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../blocks/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../blocks/head.jsp"%>
<style type="text/css">
* {
	font-size: 16px;
}
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
						<h3>LIST PRODUCT</h3>
						<a:if test="${sessionScope.success != null}">
							<div class="alert alert-success alert-dismissible fade show"
								role="alert">
								<strong>Success!</strong> ${sessionScope.success}
								${sessionScope.success = null}
								<button type="button" class="btn-close" data-bs-dismiss="alert"
									aria-label="Close"></button>
							</div>
						</a:if>

						<div class="row">
							<div class="col-md-12 col-lg-12">
								<div class="card">
									<div class="card-header">LIST PRODUCT</div>
									<div class="card-body">
										<div class="table-responsive">
											<table class="table">
												<thead>
													<tr>
														<th>ID</th>
														<th>Name</th>
														<th>Price</th>
														<th>Amount</th>
														<th width="20%">Action</th>

													</tr>
												</thead>
												<tbody>
													<a:choose>
														<a:when test="${requestScope.listProduct.size() > 0}">
															<a:forEach items="${requestScope.listProduct}"
																var="product">
																<tr>
																	<td>${product.id}</td>
																	<td>${product.name}</td>
																	<td>${product.price}</td>
																	<td>${product.amount}</td>
																	<td>
																		<div class="btn-group d-flex justify-content-end">
																			<a class="btn btn-primary"
																				href="/PROJECT/product/edit?id=${product.id}">EDIT</a>
																			<a class="btn btn-danger"
																				href="/PROJECT/product/destroy?id=${product.id}">DELETE</a>
																		</div>
																	</td>
																</tr>
															</a:forEach>
														</a:when>
														<a:otherwise>
															<tr>
																<td colspan="4" class="text-center">EMPTY</td>
															</tr>
														</a:otherwise>
													</a:choose>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end content -->
		</div>
		<!-- end main -->
	</div>
	<%@ include file="../blocks/script.jsp"%>
</body>
</html>