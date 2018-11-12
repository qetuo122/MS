<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.13/css/mdb.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.5.13/js/mdb.min.js"></script>
<style>
html, body {
	height: 100%;
}

body {
	margin: 0;
}

.container {
	min-height: 100%;
	position: relative;
}

.full {
	background-image:
		url("<%=request.getContextPath()%>/images/login-back.jpg");
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	height: 100%;
}

.area_inputs {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	margin: auto;
	width: 30%;
	height: 30%;
}
</style>
</head>
<body>
	<div class="full">
		<div class="wow flipInY"
			style="float: right; margin-top: 30px; margin-right: 30px;">
			<a href="${pageContext.request.contextPath}/"> <img
				src="${pageContext.request.contextPath}/images/back-arrow.png"
				style="height: 50px;" />
			</a>
		</div>
		<div class="container">
			<div class="area_inputs wow fadeIn" data-wow-delay="0.3s">
				<div>
					<select id="store_id" name="store_id">
						<option value="1">MS 스터디카페</option>
						<option value="2">MS PC방</option>
						<option value="3">MS 코인노래방</option>
					</select>
				</div>
				<div class="form-group">
					<label class="font-weight-bold text-white" for="inputId">아이디</label>
					<div>
						<input type="text" class="form-control" id="inputId"
							name="admin_id" value="${cookie.check.value }" placeholder="아이디">
					</div>
				</div>
				<div class="form-group">
					<label class="font-weight-bold text-white" for="inputPassword">비밀번호</label>
					<div>
						<input type="password" class="form-control" id="inputPassword"
							name="admin_pw" placeholder="비밀번호">
					</div>
				</div>
				<div class="form-group">
					<span class="font-weight-bold text-white bg-dark"
						id="spanLoginCheck"></span>
				</div>
				<div class="form-group">
					<label class="font-weight-bold text-white"> <input
						type="checkbox" id="remember" name="remember_id">
						아이디 기억하기
					</label>
					<div class="font-weight-bold text-white">
						<a href="<%=request.getContextPath()%>/user/userSearch.jsp"
							target="-blank">&nbsp; 아이디 / 비밀번호 찾기</a>
					</div>
					<div>
						<button id="loginBtn" type="submit"
							class="btn btn-danger btn-block">로그인</button>
					</div>
				</div>
				<div class="col-sm-10">
					<a class="btn btn-danger"
						href="${pageContext.request.contextPath}/admin/reg">회원가입</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	//애니메이션 메서드
	new WOW().init();

	$('#loginBtn')
			.click(
					function() {
						var id = $('#inputId').val();
						var pw = $('#inputPassword').val();
						var store = $('#store_id').val();
						var remember = $('#remember').is(':checked');
						console.log(remember);
						$
								.ajax({
									type : 'post',
									url : '${pageContext.request.contextPath}/admin',
									data : {
										// name : id
										admin_id : id,
										admin_pw : pw,
										store_id : store,
										remember_id : remember
									},
									success : function(data) {
										if (data == 0) {
											$('#spanLoginCheck').text(
													'로그인 정보를 정확히 입력해주세요.');
										} else {
											location.href = '${pageContext.request.contextPath}/admin/main';
										}
									}
								});
					});
</script>
</html>