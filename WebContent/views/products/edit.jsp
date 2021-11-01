<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ include file="../common/tag.jsp" %>

		<c:layout>
			<jsp:attribute name="content">
				<h3>Blank Page</h3>
				<div class="row d-flex justify-content-center">
					<div class="col-lg-6">
						<div class="card">
							<div class="card-header text-center">EDIT PRODUCT</div>
							<div class="card-body">
								<a:choose>
									<a:when test="${requestScope.product != null }">
										<form accept-charset="utf-8" method="POST" action="/PROJECT/product/update">
											<input type="hidden" name="id" value="${requestScope.product.id}">
											<div class="mb-3">
												<label class="form-label">Name</label> <input type="text" name="name"
													placeholder="Name" class="form-control" required=""
													value="${requestScope.product.name }">
											</div>
											<div class="mb-3">
												<label class="form-label">Price</label> <input type="number"
													name="price" placeholder="Price" class="form-control" required=""
													value="${requestScope.product.price }">
											</div>
											<div class="mb-3">
												<label class="form-label">Amount</label> <input type="text"
													name="amount" placeholder="Amount" class="form-control" required=""
													value="${requestScope.product.amount }">
											</div>
											<div class="mb-3">
												<input type="submit" class="btn btn-primary">
											</div>
										</form>
									</a:when>
									<a:otherwise>
										<h1 class="text-center">NOT FOUND</h1>
									</a:otherwise>
								</a:choose>
							</div>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</c:layout>