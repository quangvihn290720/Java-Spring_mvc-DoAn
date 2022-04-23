<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mã khuyến mãi</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="add" value="/quan-tri/web/ma-khuyen-mai/add" />
		<c:url var="get" value="/quan-tri/web/ma-khuyen-mai/edit" />
		<c:url var="deltrash" value="/quan-tri/web/ma-khuyen-mai/trash" />
		<c:url var="trash" value="/quan-tri/web/ma-khuyen-mai/thung-rac" />
		<c:url var="onoff" value="/quan-tri/web/ma-khuyen-mai/status" />
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
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>${msgfail}</strong>
					</div>
				</c:if>
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách mã khuyến mãi</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success" href="${add}"><i
							class="fas fa-plus"></i> Thêm</a> <a class="btn btn-sm btn-danger"
							href="${trash}"><i class="fas fa-trash"></i> Thùng rác</a>
					</div>


				</div>
				<div class="card-body">
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Mã code</th>
								<th>Giá giảm</th>
								<th>Bắt đầu khuyến mãi</th>
								<th>Hết hạn khuyến mãi</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ couponPaginate }">
								<tr>
									<td>${item.id}</td>
									<td>${item.code}</td>
									<td><fmt:formatNumber value="${item.pricesale}"
											type="number" /> đ</td>
									<td>${item.start}</td>
									<td>${item.end}</td>
									<td><c:choose>
											<c:when test="${item.status == 1}">
												<a href="${onoff}/${item.id}"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-info"><i class="fas fa-toggle-on"></i></a>
											</c:when>
											<c:otherwise>
												<a href="${onoff}/${item.id}"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-danger"><i
													class="fas fa-toggle-off"></i></a>
											</c:otherwise>
										</c:choose> <a href="${deltrash}/${item.id}"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th>Mã code</th>
								<th>Giá giảm</th>
								<th>Bắt đầu khuyến mãi</th>
								<th>Hết hạn khuyến mãi</th>
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
									href="<c:url value="/quan-tri/web/ma-khuyen-mai/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/web/ma-khuyen-mai/${loop.index}"/>">${loop.index}</a></li>
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