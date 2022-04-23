<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tìm kiếm</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="detail" value="/quan-tri/hoa-don" />
		<c:url var="changestatus" value="/quan-tri/hoa-don/status" />
		<c:url var="delete" value="/quan-tri/hoa-don/delete" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-danger text-uppercase">Danh sách đơn
							hàng</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-danger" href="{{route('order.trash')}}"><i
							class="fas fa-trash"></i> Thùng rác</a>
					</div>

				</div>
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
							<c:forEach var="item" items="${ searchPaginate }">
								<tr>
									<td>${item.id }</td>
									<td>${item.email }</td>
									<td>${item.display_name }</td>
									<td>${item.created_at}</td>
									<td><c:choose>
											<c:when test="${item.status ==0}">
												Chưa hoàn thành
											</c:when>
											<c:when test="${item.status ==1}">
												Hoàn thành
											</c:when>
										</c:choose></td>
									<td><a href="${changestatus }/${item.id } "
										class="btn btn-sm btn-info" data-toggle="tooltip"
										data-placement="bottom" title="Đưa vào thùng rác"><i
											class="fas fa-exchange-alt"></i></a> <a
										href="${delete }/${item.id } " class="btn btn-sm btn-danger"
										data-toggle="tooltip" data-placement="bottom"
										title="Đưa vào thùng rác"><i class="fas fa-trash"></i></a></td>
									<td><a href="${detail }/${item.id }">Chi tiết</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
				<div class="card-footer">Footer</div>
				<!-- /.card-footer-->
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/search?q=${q}&currentPage=${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/search?q=${q}&currentPage=${loop.index}"/>">${loop.index}</a></li>
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