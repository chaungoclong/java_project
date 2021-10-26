<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="../blocks/tag.jsp"%>
	<!DOCTYPE html>
	<html>
	<head>
		<%@ include file="../blocks/head.jsp"%>
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
		                        <h3>Blank Page</h3>
                                <div class="row d-flex justify-content-center">
                                    <div class="col-lg-6">
                                        <div class="card">
                                            <div class="card-header text-center">CREATE PRODUCT</div>
                                            <div class="card-body">
                                                <form accept-charset="utf-8" method="POST" action="/PROJECT/product/store">
                                                    <div class="mb-3">
                                                        <label class="form-label">Name</label>
                                                        <input type="text" name="name" placeholder="Name" class="form-control" required="">
                                                    </div>
                                                   <div class="mb-3">
                                                        <label class="form-label">Price</label>
                                                        <input type="number" name="price" placeholder="Price" class="form-control" required="">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label class="form-label">Amount</label>
                                                        <input type="text" name="amount" placeholder="Amount" class="form-control" required="">
                                                    </div>
                                                    <div class="mb-3">
                                                        <input type="submit" class="btn btn-primary">
                                                    </div>
                                                </form>
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