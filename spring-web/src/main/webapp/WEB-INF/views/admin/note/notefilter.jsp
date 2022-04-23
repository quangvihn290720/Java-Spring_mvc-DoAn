<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nhật ký chỉnh sửa</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="delete" value="/quan-tri/web/nhat-ky/delete" />
		<c:url var="list" value="/quan-tri/web/nhat-ky" />
		<c:url var="search" value="/quan-tri/web/nhat-ky/search" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Nhật ký chỉnh
							sửa</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success" href="${list}"> Quay lại</a>
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
					<div style="margin-bottom: 1rem;">
						<form class="" action="${search}" method="GET">
							Từ: <input class="" type="date" name="to"> đến: <input
								class="" type="date" name="from"> <input type="hidden"
								name="page" value="1">
							<button type="submit" class="btn btn-info">Tìm</button>
						</form>
					</div>
					<table class="table table-bordered border-hover pt-3	">
						<thead>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Nội dung</th>
								<th>Người chỉnh sửa</th>
								<th>Thời gian</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ notebytimePaginate }">
								<tr>
									<td>${item.id}</td>
									<td>${item.content}</td>
									<td>${mapUser[item.created_by]}</td>
									<td>${item.created_at}</td>
									<td><a href="${delete}/${item.id}"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										title="Xóa nhật ký" class="btn btn-sm btn-danger"><i
											class="fas fa-trash"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Nội dung</th>
								<th>Người chỉnh sửa</th>
								<th>Thời gian</th>
								<th style="width: 10rem;">Chức năng</th>
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
									href="<c:url value="/quan-tri/web/nhat-ky?page=${loop.index}&to=${to}&from=${from}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/web/nhat-ky?page=${loop.index}&to=${to}&from=${from}"/>">${loop.index}</a></li>
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