<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thành viên</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<!-- Main content -->
		<section class="content">
			<!-- Default box -->
			<div class="card">
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
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách tài
							khoản người dùng</strong>
					</h3>
				</div>
				<div class="card-body">
					<table class="table table-hover border table-bordered">
						<thead class="thead-light">
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên tài khoản</th>
								<th style="width: 200px;">Tên người dùng</th>
								<th>Vai trò</th>
								<th>Vai trò phụ</th>
								<th>Đổi vai trò</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userPaginate}" var="item">
								<tr>
									<td>${item.user_id}</td>
									<td><a
										href="<c:url value="/quan-tri/web/chi-tiet-tai-khoan/${item.user_id}"/>">${item.username}</a></td>
									<td>${item.user_fullname}</td>
									<td>${item.role}</td>
									<td>${mapRole[item.role2]}</td>
									<td><a
										href="<c:url value="/quan-tri/web/tai-khoan/cap-nhat-quyen/${item.user_id}"/>"
										title="Đổi vai trò người dùng">Đổi vai trò</a></td>
									<td><a
										href="<c:url value="/quan-tri/web/tai-khoan/xoa-tai-khoan/${item.user_id}"/>"
										title="Xóa tài khoản" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng không?');"><i class="fas fa-user-slash"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pt-4"></div>
				</div>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/web/tai-khoan/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/web/tai-khoan/${loop.index}"/>">${loop.index}</a></li>
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