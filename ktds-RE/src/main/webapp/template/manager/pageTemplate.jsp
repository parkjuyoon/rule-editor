<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav>
	<ul class="pagination">
		<li>
			<a href="#" aria-label="Previous">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
		<c:forEach var="i" begin="${param.firstPageNum }" end="${param.pageCnt }" step="1">
			<c:choose>
				<c:when test="${param.curPage eq i }">
					<li class="active">
						<a href="#">${i }</a>
					</li>
				</c:when>
				<c:otherwise>
					<li>
						<a href="#">${i }</a>
					</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<li>
			<a href="#" aria-label="Next">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
	</ul>
</nav>

