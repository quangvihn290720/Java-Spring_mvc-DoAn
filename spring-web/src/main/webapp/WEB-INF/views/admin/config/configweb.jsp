<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cấu hình website</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="edit" value="/quan-tri/web/cau-hinh-web/edit" />
		<c:url var="onoff" value="/quan-tri/web/cau-hinh-web/status" />
		<!-- Main content -->
		<section class="content">
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Chi tiết
							website</strong>
					</h3>
					<div class="card-tools">
						<c:choose>
							<c:when test="${config.status == 0}">
								<a class="btn btn-sm btn-danger" href="${onoff}/${config.id}">
									Tắt bảo trì</a>
							</c:when>
							<c:when test="${config.status == 1}">
								<a class="btn btn-sm btn-success" href="${onoff}/${config.id}">
									Bật bảo trì</a>
							</c:when>

						</c:choose>
						<a class="btn btn-sm btn-info" href="${edit}/${config.id}"><i
							class="fas fa-plus"></i> Chỉnh sửa</a>
					</div>
				</div>
				<div class="card-body">

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
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin website</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên:</th>
								<td>${config.nameweb}</td>
							</tr>
							<tr>
								<th>Mô tả:</th>
								<td>${config.web_detail}</td>
							</tr>
							<tr>
								<th>Hotline:</th>
								<td>${config.hotline}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${config.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ:</th>
								<td>${config.address_store}</td>
							</tr>
							<tr>
								<th>Logo website:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/images/${config.logo}"
									alt=""></td>
							</tr>
							<tr>
								<th>Logo moblie:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/images/${config.logomobile}"
									alt=""></td>
							</tr>
							<tr>
								<th>Icon website:</th>
								<td><img class="img-logo"
									src="${pageContext.request.contextPath}/images/${config.icon}"
									alt=""></td>
							</tr>
						</tbody>
					</table>
					<div class="pt-4"></div>
				</div>
			</div>
			<!-- /.card -->

		</section>
		<!-- /.content -->
	</div>
</body>
</html>