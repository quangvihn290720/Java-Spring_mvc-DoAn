<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thùng rác tiện ích</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="delete" value="/quan-tri/tien-ich/delete" />
		<c:url var="retrash" value="/quan-tri/tien-ich/retrash" />
		<c:url var="list" value="/quan-tri/tien-ich" />
		<!-- Main content -->
		<section class="content">
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
			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Thùng rác tiện ích sản
							phẩm</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-danger" href="${list}"> Quay về danh
							sách</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên sản phẩm</th>
								<th>Tiện ích</th>
								<th style="width: 300px;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${utilTrashPaginate.size()>0}">
								<c:forEach var="item" items="${ utilTrashPaginate }">
									<tr>
										<td>${item.utilities_id}</td>
										<td>${mapProd[item.product_id]}</td>
										<td>${item.utilitiesname}</td>
										<td><a href="${retrash}/${item.utilities_id}"
											onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
											class="btn btn-sm btn-info"><i class="fas fa-undo"></i></a> <a
											href="${delete}/${item.utilities_id}"
											onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
											class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên sản phẩm</th>
								<th>Tiện ích</th>
								<th style="width: 300px;">Chức năng</th>
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
									href="<c:url value="/quan-tri/tien-ich/thung-rac/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/tien-ich/thung-rac/${loop.index}"/>">${loop.index}</a></li>
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