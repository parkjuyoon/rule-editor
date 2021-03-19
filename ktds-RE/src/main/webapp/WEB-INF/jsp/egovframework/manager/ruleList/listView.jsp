<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rule List</title>

<script type="text/javascript" src="/common/js/jquery-3.6.0.min.js"></script>
<script src="/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/bootstrap-3.3.2-dist/css/bootstrap-theme.min.css">

<script type="text/javascript" src="/common/js/pageUtil.js"></script>
<script type="text/javascript" src="/js/manager/ruleManager/ruleList.js"></script>

</head>
<body>
	<div>
		<table class="table table-hover">
			<caption>Rule List</caption>
			<colgroup>
				<col width="10%" />
				<col width="*" />
				<col width="15%" />
				<col width="15%" />
				<col width="10%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
					<th>조회수</th>
					<th>조회수</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rule" items="${ruleList }">
					<tr>
						<td>${rule.CUST_ACC_NO }</td>
						<td>${rule.SEX_TYPE_NM }</td>
						<td>${rule.CUST_CTG_TYPE_NM }</td>
						<td>${rule.INDV_INFO_COLEC_USE_AGREE_YN }</td>
						<td>${rule.SMPH_USE_YN }</td>
						<td>${rule.CUST_CLAS_NM }</td>
						<td>${rule.EC_CUST_YN }</td>
						<td>${rule.CUST_AGE }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="paging"></div>
</body>
</html>

