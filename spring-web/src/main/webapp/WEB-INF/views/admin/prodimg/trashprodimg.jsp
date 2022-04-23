<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thùng rác hình ảnh sản phẩm</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="delete" value="/quan-tri/hinh-anh-san-pham/delete" />
		<c:url var="retrash" value="/quan-tri/hinh-anh-san-pham/retrash" />
		<c:url var="list" value="/quan-tri/hinh-anh-san-pham" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách hình
							ảnh sản phẩm</strong>
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
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Tên sản phẩm</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ prodimgTrashPaginate }">
								<tr>
									<td>${item.id}</td>
									<td>${mapProd[item.product_id]}</td>
									<td><img style="width: 100%;"
										src="${pageContext.request.contextPath}/images/${item.img}"></td>
									<td><a href="${retrash}/${item.id}"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-info"><i class="fas fa-undo"></i></a> <a
										href="${delete}/${item.id}"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Tên sản phẩm</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Chức năng</th>
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
									href="<c:url value="/quan-tri/hinh-anh-san-pham/thung-rac/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/hinh-anh-san-pham/thung-rac/${loop.index}"/>">${loop.index}</a></li>
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