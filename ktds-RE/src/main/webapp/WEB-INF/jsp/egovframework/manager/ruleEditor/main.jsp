<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<script type="text/javascript" src="/common/js/jquery-3.6.0.min.js"></script>
<script src="/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<script src="/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<link href="/common/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="/js/manager/ruleEditor/ruleEditor.js"></script>
<script type="text/javascript" src="/js/manager/ruleEditor/drlGenerator.js"></script>
<link rel="stylesheet" type="text/css" href="/css/egovframework/manager/ruleEditor/ruleEditor.css">

</head>
<body>
	<div id="wrapper" class="animate">
		<nav class="navbar header-top fixed-top navbar-expand-lg  navbar-dark bg-dark">
			<span class="navbar-toggler-icon leftmenutrigger"></span>
			<a class="navbar-brand" href="#">KTDS Rule Editor</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav animate side-nav">
					<li class="nav-item">
						<a class="nav-link" href="#">
							Home <span class="sr-only">(current)</span>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Side Menu Items</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Pricing</a>
					</li>
				</ul>
				<ul class="navbar-nav ml-md-auto d-md-flex">
					<li class="nav-item">
						<a class="nav-link" href="#">
							Home <span class="sr-only">(current)</span>
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Top Menu Items</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="#">Pricing</a>
					</li>
				</ul>
			</div>
		</nav>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<div class="card">
						<div class="card-body h834">
							<h5 class="card-title">속성 VIEW</h5>
							<fieldset class="attViewTree">
								<legend></legend>
								<ul>
									<c:forEach var="table" items="${tableList }">
										<li data-table_name="${table.TABLE_NAME }">${table.TABLE_COMMENT }</li>
									</c:forEach>
								</ul>
							</fieldset>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="row">
						<div class="col-md-2">
							<div class="row">
								<div class="col">
									<div class="card">
										<div class="card-body h250">
											<h5 class="card-title">논리연산 VIEW</h5>
											<fieldset class="">
												<legend></legend>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios1" value="logical1" checked>
														<span> == </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios2" value="logical2">
														<span> > </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios3" value="logical3">
														<span> < </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios4" value="logical4">
														<span> >= </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios5" value="logical5">
														<span> <= </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios6" value="logical6">
														<span> IN </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="logicalRadios" id="logicalRadios7" value="logical7">
														<span> NOT IN </span>
													</label>
												</div>
											</fieldset>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="card">
										<div class="card-body h250">
											<h5 class="card-title">관계연산 VIEW</h5>
											<fieldset class="">
												<legend></legend>
												<div class="radio">
													<label> 
														<input type="radio" name="relationRadios" id="relationRadios1" value="relation1" checked>
														<span> AND </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="relationRadios" id="relationRadios2" value="relation2">
														<span> OR </span>
													</label>
												</div>
												<div class="radio">
													<label> 
														<input type="radio" name="relationRadios" id="relationRadios3" value="relation3">
														<span> NONE </span>
													</label>
												</div>
											</fieldset>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card">
								<div class="card-body h517" id="detAttrDiv">
									<h5 class="card-title">상세 속성 VIEW</h5>
									<h6 class="card-subtitle mb-2 text-muted">
										<span id="detAttrTable">속성을 선택하세요.</span>
										<span id="detAttrColumn"></span>
									</h6>
									<fieldset class="">
										<legend></legend>
										<div id="detAttrData"></div>
										<button type="button" class="btn btn-info" id="attrAddBtn">속성 추가</button>
									</fieldset>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card">
								<div class="card-body h517" id="ruleAttDiv">
									<h5 class="card-title">Rule 속성 (<span id="ruleAttCnt">0</span> 건 추가)</h5>
									<h6 class="card-subtitle mb-2 text-muted">Rule Name : <input type="text" placeholder="Rule Name을 입력하세요." id="ruleName"/></h6>
									<button type="button" class="btn btn-info" id="ruleAddBtn">Rule 추가</button>
									<fieldset class="">
										<legend></legend>
										<div id="ruleAttrData"></div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-body h300">
									<h5 class="card-title">DRL Generated</h5>
									<h6 class="card-subtitle mb-2 text-muted">
										<span id="detAttrTable">적용될 DRL을 생성합니다.</span>
									</h6>
									<button type="button" class="btn btn-info" id="drlGenBtn">DRL 생성</button>
									<button type="button" class="btn btn-info" id="ruleApplyBtn">Rule 적용</button>
									<fieldset class="">
										<legend></legend>
										<div>
											<pre class="h240" id="drlGenData"></pre>
										</div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>