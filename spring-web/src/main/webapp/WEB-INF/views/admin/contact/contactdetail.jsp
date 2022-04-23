<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết liên hệ</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="list" value="/quan-tri/lien-he" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-danger text-uppercase">Chi tiết liên
							hệ</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-danger" href="${list }"><i
							class="fas fa-backward"></i> Quay lại</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin người liên hệ</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên:</th>
								<td>${itemcontact.name }</td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td>${itemcontact.phone}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${itemcontact.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ:</th>
								<td>${itemcontact.address}</td>
							</tr>
							<tr>
								<th>Ngày:</th>
								<td>${itemcontact.created_at}</td>
							</tr>
						</tbody>
					</table>
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Nội dung</h3>
						</thead>
						<tbody>
							<tr>
								<td style="width: 150px;">Tiêu đề</td>
								<td>${ itemcontact.subject}</td>
							</tr>
							<tr>
								<td style="width: 150px;">Lời nhắn</td>
								<td colspan="5" class="	 pr-5">${ itemcontact.content}</td>
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