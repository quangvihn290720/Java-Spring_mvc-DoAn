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
	<div class="content-wrapper pt-3">
		<!-- Main content -->
		<section class="content">
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-danger text-uppercase">Chi tiết tài
							khoản</strong>
					</h3>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin tài khoản</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên tài khoản:</th>
								<td>${loginInfo.username }</td>
							</tr>
							<tr>
								<th>Tên người dùng:</th>
								<td>${loginInfo.user_fullname }</td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td>${loginInfo.user_phone}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${loginInfo.user_email}</td>
							</tr>
							<tr>
								<th>Giới tính:</th>
								<td>${loginInfo.user_gender}</td>
							</tr>
							<tr>
								<th>Vai trò chính:</th>
								<td>${loginInfo.role}</td>
							</tr>
							<tr>
								<th>Vai trò phụ:</th>
								<td>${loginInfo.roleEntity.code}</td>
							</tr>
							<tr>
								<th>Hình đại diện:</th>
								<td>${loginInfo.user_img}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
				<div class="card-footer">Footer</div>
				<!-- /.card-footer-->
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
</body>
</html>