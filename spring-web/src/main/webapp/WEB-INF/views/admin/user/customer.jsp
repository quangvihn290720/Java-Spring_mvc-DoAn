<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Khách hàng</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<!-- Main content -->
		<section class="content">
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách khách hàng</strong>
					</h3>
					<div class="card-tools">
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
						<div class="alert alert-success alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>${msgfail}</strong>
						</div>
					</c:if>
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th>Email khách hàng</th>
								<th>Tên khách hàng</th>
								<th>Số điện thoại</th>
								<th>Địa chỉ</th>
								<th style="width: 100px;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${customerPaginate.size()>0}">
								<c:forEach var="item" items="${ customerPaginate }">
									<tr>
										<td>${item.id}</td>
										<td>${item.email}</td>
										<td>${item.display_name}</td>
										<td>${item.phone}</td>
										<td>${item.address}, ${item.province}, ${item.district}, ${item.city}</td>
										<td></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th>Email khách hàng</th>
								<th>Tên khách hàng</th>
								<th>Số điện thoại</th>
								<th>Địa chỉ</th>
								<th style="width: 100px;">Chức năng</th>
							</tr>
						</tfoot>
					</table>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/khach-hang/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/khach-hang/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</nav>
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
</body>
</html>