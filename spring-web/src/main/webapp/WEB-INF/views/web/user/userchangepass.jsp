<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin tài khoản</title>
</head>
<body>
	<c:url var="changepass" value="/tai-khoan/doi-mat-khau" />
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="<c:url value='/'/>"><span>Trang
									chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Trang
									khách hàng</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<div class="row py-3">
				<div class="col-xs-12 col-sm-12 col-lg-3 col-left-ac">
					<div class="left-account">
						<h5 class="title-account">Thông tin tài khoản</h5>
						<p>Xin chào, ${LoginInfo.user_fullname}!</p>
						<ul>
							<li><a href="<c:url value='/tai-khoan'/>" class="title-info">Thông
									tin tài khoản</a></li>
							<li><a href="<c:url value='/tai-khoan/don-hang'/>"
								class="title-info">Đơn hàng của bạn</a></li>
							<li><a href="<c:url value='/tai-khoan/doi-mat-khau'/>"
								class="title-info">Đổi mật khẩu</a></li>
							<li><a href="<c:url value='/dang-xuat'/>" class="title-info">Đăng
									xuất</a></li>
						</ul>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-lg-9 col-right-ac">
					<h1 class="title-head">Đổi mật khẩu</h1>
					<div class="changepw">
						<form id="change_customer_password" action="${changepass }" method="POST">
							<p>Để đảm bảo tính bảo mật vui lòng đặt mật khẩu với ít nhất
								8 kí tự, viết hoa chữ cái đầu</p>
							<c:if test="${not empty msg}">
								<div class="alert alert-success alert-dismissible" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>${msg}</strong>
								</div>
							</c:if>
							<c:if test="${not empty msgfail}">
								<div class="alert alert-danger alert-dismissible" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>${msgfail}</strong>
								</div>
							</c:if>
							<div class="form-changepw">
								<div class="form-group">
									<label>Mật khẩu cũ</label> <input type="password"
										class="form-control" id="password" placeholder="Password" name="password">
								</div>
								<div class="form-group">
									<label>Mật khẩu mới</label> <input type="password"
										class="form-control" id="newpassword" placeholder="Password" name = "newpassword">
								</div>
								<div class="form-group">
									<label>Xác nhận lại mật khẩu</label> <input
										type="password" class="form-control"
										id="confirmpassword" placeholder="Password" name="confirmpassword">
								</div>
								<button type="submit" class="btn btn-primary">Đặt lại
									mật khẩu</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		var password = document.getElementById("newpassword"), confirm_password = document
				.getElementById("confirmpassword");

		function validatePassword() {
			if (password.value != confirm_password.value) {
				confirm_password
						.setCustomValidity("Mật khẩu xác nhận không chính xác");
			} else {
				confirm_password.setCustomValidity('');
			}
		}
		password.onchange = validatePassword;
		confirm_password.onkeyup = validatePassword;
	</script>
</body>
</html>