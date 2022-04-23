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
			<div class="row pt-3">
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
					<h1 class="title-head">Hồ Sơ Của Tôi</h1>
					<p>Quản lý thông tin hồ sơ để bảo mật tài khoản</p>
					<br>
					<div class="form-info">
						<p>
							<strong>Tên đăng nhập:</strong> ${LoginInfo.username}
						</p>
						<p>
							<strong>Họ tên:</strong> ${LoginInfo.user_fullname}
						</p>
						<p>
							<strong>Email:</strong> ${LoginInfo.user_email}
						</p>
						<p>
							<strong>Số điện thoại:</strong>${LoginInfo.getUser_phone().replaceAll("\\d(?=(?:\\D*\\d){3})", "*")}
						</p>
						<p>
							<strong>Giới tính:</strong> ${LoginInfo.user_gender}
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>