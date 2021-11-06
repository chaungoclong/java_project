<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="append"%>

<a:set var="currentURL" value="${requestScope.currentURL}" />
<a:set var="currentPage" value="${requestScope.currentPage}" />
<a:set var="totalPage" value="${requestScope.totalPage}" />
<a:set var="queryString" value="${requestScope.queryString}" />

<nav aria-label="...">
	<ul class="pagination">
		<!-- previous page -->
		<a:if test="${totalPage > 1 && currentPage > 1}">
			<a:url var="prevURL" value="${currentURL}?page=${currentPage - 1}" />

			<a:if test="${append != null}">
				<a:url var="prevURL"
					value="${currentURL}?page=${currentPage - 1}&${append}" />
			</a:if>

			<li class="page-item"><a class="page-link" href="${prevURL}">Previous</a></li>
		</a:if>
		<!-- previous page -->

		<a:forEach var="i" begin="1" end="${requestScope.totalPage}" step="1">
			<a:url var="url" value="${currentURL}?page=${i}" />

			<a:if test="${append != null}">
				<a:url var="url" value="${currentURL}?page=${i}&${append}" />
			</a:if>

			<!-- current page -->
			<a:if test="${i == currentPage}">
				<li class="page-item active"><a class="page-link"
					href="${url}">${i} <span class="sr-only">(current)</span>
				</a></li>
			</a:if>
			<!-- current page -->

			<!-- other page -->
			<a:if test="${i != currentPage}">
				<li class="page-item"><a class="page-link" href="${url}">${i}</a></li>
			</a:if>
			<!-- other page -->
		</a:forEach>

		<!-- next page -->
		<a:if test="${totalPage > 1 && currentPage < totalPage}">
			<a:url var="nextURL" value="${currentURL}?page=${currentPage + 1}" />

			<a:if test="${append != null}">
				<a:url var="nextURL"
					value="${currentURL}?page=${currentPage + 1}&${append}" />
			</a:if>

			<li class="page-item"><a class="page-link" href="${nextURL}">Next</a></li>
		</a:if>
		<!-- next page -->
	</ul>
</nav>