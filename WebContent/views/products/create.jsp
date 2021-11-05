<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../common/tag.jsp" %>

        <c:layout>
            <jsp:attribute name="content">
                <h3>Blank Page</h3>
                <div class="row d-flex justify-content-center">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-header text-center">CREATE PRODUCT</div>
                            <div class="card-body">
                                <form accept-charset="utf-8" method="POST" action="/PROJECT/product/store" id="formCreate">
                                    <div class="mb-3">
                                        <label class="form-label">Name</label>
                                        <input type="text" name="name" placeholder="Name" class="form-control"
                                            rules="required|min_length:2|max_length:100">
                                        <div class="msg"></div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Price</label>
                                        <input type="text" name="price" placeholder="Price" class="form-control"
                                            rules="required|is_number">
                                        <div class="msg"></div>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Amount</label>
                                        <input type="text" name="amount" placeholder="Amount" class="form-control"
                                             rules="required|is_number">
                                        <div class="msg"></div>
                                    </div>
                                    <div class="mb-3">
                                        <input type="submit" class="btn btn-primary">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </jsp:attribute>
            
            <jsp:attribute name="js">
            	<script>
            		var validate = new Validate("#formCreate", {
            			message: {
            				selector: ".msg",
            				whenError: "alert alert-danger"
            			}
        
            		})
            	</script>
            </jsp:attribute>
        </c:layout>