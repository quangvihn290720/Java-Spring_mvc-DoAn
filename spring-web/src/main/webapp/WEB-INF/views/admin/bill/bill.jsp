<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hóa đơn</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="detail" value="/quan-tri/hoa-don/chi-tiet" />
		<c:url var="changestatus" value="/quan-tri/hoa-don/status" />
		<c:url var="delete" value="/quan-tri/hoa-don/delete" />
		<c:url var="search" value="/quan-tri/search" />
		<c:url var="export" value="/quan-tri/hoa-don/exportExcel" />
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
						<strong class="text-danger text-uppercase">Danh sách đơn
							hàng</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success" href="${export }"><i
							class="fas fa-file-excel"></i> Xuất hóa đơn</a>
					</div>

				</div>
				<form class="" action="${search}" method="GET">
					<div class="input-group input-group-sm">
						<input class="form-control form-control-navbar" type="search"
							name="q" placeholder="Search" aria-label="Search"> <input
							type="hidden" name="currentPage" value="1">
						<div class="input-group-append">
							<button class="btn btn-navbar" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</div>
					</div>
				</form>
				<div class="card-body">
					<table class="table table-bordered border-hover">
						<thead class="thead-dark">
							<tr>
								<th>ID</th>
								<th>Email</th>
								<th>ID khách hàng</th>
								<th>Ngày đặt</th>
								<th>Tình trạng</th>
								<th>Chức năng</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${billPaginate}">
								<tr>
									<td>${item.id }</td>
									<td>${item.email }</td>
									<td>${item.display_name }</td>
									<td>${item.created_at}</td>
									<td><c:choose>
											<c:when test="${item.status == -1}">
												Đã hủy
											</c:when>
											<c:when test="${item.status == 0}">
												Đặt hàng
											</c:when>
											<c:when test="${item.status == 1}">
												Đã xác nhận
											</c:when>
											<c:when test="${item.status ==2}">
												Đã giao cho ĐVVC
											</c:when>
											<c:when test="${item.status ==3}">
												Đang vận chuyển
											</c:when>
											<c:when test="${item.status ==4}">
												Đã giao hàng
											</c:when>
										</c:choose></td>
									<td><a
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										href="${delete }/${item.id } " class="btn btn-sm btn-danger"
										data-toggle="tooltip" data-placement="bottom"
										title="Xóa đơn hàng"><i class="fas fa-trash"></i></a></td>
									<td><a href="${detail }/${item.id }">Chi tiết</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
				<!-- /.card-footer-->
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/hoa-don/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/hoa-don/${loop.index}"/>">${loop.index}</a></li>
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