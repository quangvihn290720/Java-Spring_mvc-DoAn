<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết tài khoản</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="list" value="/quan-tri/web/tai-khoan/" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-danger text-uppercase">Chi tiết tài
							khoản</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-danger" href="${list }"><i
							class="fas fa-backward"></i> Quay lại</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin tài khoản</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên tài khoản:</th>
								<td>${user.username }</td>
							</tr>
							<tr>
								<th>Tên người dùng:</th>
								<td>${user.user_fullname }</td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td>${user.user_phone}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${user.user_email}</td>
							</tr>
							<tr>
								<th>Giới tính:</th>
								<td>${user.user_gender}</td>
							</tr>
							<tr>
								<th>Vai trò chính:</th>
								<td>${user.role}</td>
							</tr>
							<tr>
								<th>Vai trò phụ:</th>
								<td>${mapRole[user.role2]}</td>
							</tr>
							<tr>
								<th>Hình đại diện:</th>
								<td>${user.user_img}</td>
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