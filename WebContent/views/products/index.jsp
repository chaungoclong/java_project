<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ include file="../common/tag.jsp" %>
		${requestScope.totalPage}
		<c:layout>
			<jsp:attribute name="css">
				<style>
					* {
						font-size: 16px;
					}
				</style>
			</jsp:attribute>

			<jsp:attribute name="content">
				<h3>LIST PRODUCT</h3>
				<a:if test="${sessionScope.success != null}">
					<div class="alert alert-success alert-dismissible fade show" role="alert">
						<strong>Success!</strong> ${sessionScope.success}
						${sessionScope.success = null}
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
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
													<a:forEach items="${requestScope.listProduct}" var="product">
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
									<c:links append="a=${requestScope.totalPage}"/>
								</div>
							</div>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</c:layout>