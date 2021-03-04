<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<script type="text/javascript" src="/common/js/jquery-3.6.0.min.js"></script>
<script src="/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<link href="/common/css/bootstrap.min.css" rel="stylesheet">

<style type="text/css">
body {
	background: #f9f9f9;
}

#wrapper {
	padding: 90px 15px;
}

.navbar-expand-lg .navbar-nav.side-nav {
	flex-direction: column;
}

.card {
	margin-bottom: 15px;
	border-radius: 0;
	box-shadow: 0 3px 5px rgba(0, 0, 0, .1);
}

.header-top {
	box-shadow: 0 3px 5px rgba(0, 0, 0, .1)
}

.leftmenutrigger {
	display: none
}

.h517 {
	height: 517px;
}

.h300 {
	height: 300px;
}

.h834 {
	height: 834px;
}

.h250 {
	height: 250px;
}

#ruleAddBtn {
	position: absolute;
    bottom: 0;
    right: 0;
    margin: 10px;
}

#ruleApplyBtn {
	position: absolute;
    bottom: 0;
    right: 0;
    margin: 10px;
}

#detailAttDiv {
	position: relative;
}

#ruleAttDiv {
	position: relative;
}

@media ( min-width :992px) {
	.leftmenutrigger {
		display: block;
		display: block;
		margin: 7px 20px 4px 0;
		cursor: pointer;
	}
	#wrapper {
		padding: 90px 15px 15px 15px;
	}
	.navbar-nav.side-nav.open {
		left: 0;
	}
	.navbar-nav.side-nav {
		background: #585f66;
		box-shadow: 2px 1px 2px rgba(0, 0, 0, .1);
		position: fixed;
		top: 56px;
		flex-direction: column !important;
		left: -220px;
		width: 200px;
		overflow-y: auto;
		bottom: 0;
		overflow-x: hidden;
		padding-bottom: 40px
	}
}

.animate {
	-webkit-transition: all .3s ease-in-out;
	-moz-transition: all .3s ease-in-out;
	-o-transition: all .3s ease-in-out;
	-ms-transition: all .3s ease-in-out;
	transition: all .3s ease-in-out
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('.leftmenutrigger').on('click', function(e) {
			$('.side-nav').toggleClass("open");
			e.preventDefault();
		});
	});
</script>
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
							<h6 class="card-subtitle mb-2 text-muted">Bootstrap 4.0.0 Snippet by pradeep330</h6>
							<p class="card-text">You can also try different version of Bootstrap V4 side menu. Click below link to view all Bootstrap Menu versions.</p>
							<a href="https://bootsnipp.com/pradeep330" class="card-link">link</a>
							<a href="http://websitedesigntamilnadu.com" class="card-link">Another link</a>
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
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios1" value="option1" checked> =
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios2" value="option2"> >
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios3" value="option3"> <
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios4" value="option4"> >=
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios5" value="option5"> <=
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios6" value="option6"> IN
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="logicalRadios" id="logicalRadios7" value="option7"> NOT IN
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col">
									<div class="card">
										<div class="card-body h250">
											<h5 class="card-title">관계연산 VIEW</h5>
											<div class="radio">
												<label> <input type="radio" name="relationRadios" id="relationRadios1" value="option1" checked> AND
												</label>
											</div>
											<div class="radio">
												<label> <input type="radio" name="relationRadios" id="relationRadios2" value="option2"> OR
												</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card">
								<div class="card-body h517" id="detailAttDiv">
									<h5 class="card-title">상세 속성 VIEW</h5>
									<h6 class="card-subtitle mb-2 text-muted">Bootstrap 4.0.0 Snippet by pradeep330</h6>
									<p class="card-text">You can also try different version of Bootstrap V4 side menu. Click below link to view all Bootstrap Menu versions.</p>
									<button type="button" class="btn btn-info" id="ruleAddBtn">Rule Add</button>
<!-- 									<a href="https://bootsnipp.com/pradeep330" class="card-link">link</a> -->
<!-- 									<a href="http://websitedesigntamilnadu.com" class="card-link">Another link</a> -->
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card">
								<div class="card-body h517" id="ruleAttDiv">
									<h5 class="card-title">Rule 속성</h5>
									<h6 class="card-subtitle mb-2 text-muted">Rule Name : 유선전화 사용자 인터넷 전환대상</h6>
									<button type="button" class="btn btn-info" id="ruleApplyBtn">Rule Apply</button>
									<p class="card-text">You can also try different version of Bootstrap V4 side menu. Click below link to view all Bootstrap Menu versions.</p>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-body h300">
									<h5 class="card-title">DRL Generated</h5>
									<h6 class="card-subtitle mb-2 text-muted">Bootstrap 4.0.0 Snippet by pradeep330</h6>
									<p class="card-text">You can also try different version of Bootstrap V4 side menu. Click below link to view all Bootstrap Menu versions.</p>
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