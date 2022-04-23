<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lựa chọn chi tiết sản phẩm</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="add" value="/quan-tri/tuy-chon-san-pham/add" />
		<c:url var="get" value="/quan-tri/tuy-chon-san-pham/get" />
		<c:url var="edit" value="/quan-tri/tuy-chon-san-pham/edit" />
		<c:url var="onoff" value="/quan-tri/tuy-chon-san-pham/status" />
		<c:url var="deltrash" value="/quan-tri/tuy-chon-san-pham/trash" />
		<c:url var="trash" value="/quan-tri/tuy-chon-san-pham/thung-rac" />
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
						<strong class="text-uppercase text-danger">Danh sách sản
							phẩm </strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success" href="${add}"><i
							class="fas fa-plus"></i> Thêm</a> <a class="btn btn-sm btn-danger"
							href="${trash }"><i class="fas fa-trash"></i> Thùng rác</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên sản phẩm</th>
								<th>Nhóm tùy chọn</th>
								<th>Tùy chọn</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ prodoptionPaginate }">
								<tr>
									<td>${item.productoptions_id}</td>
									<td>${mapProd[item.product_id]}</td>
									<td>${mapOpGroup[item.optiongroup_id]}</td>
									<td>${mapOption[item.option_id]}</td>
									<td><c:choose>
											<c:when test="${item.status==1}">
												<a href="${onoff }/${item.productoptions_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-success"><i
													class="fas fa-toggle-on"></i></a>
											</c:when>
											<c:when test="${item.status==2}">
												<a href="${onoff }/${item.productoptions_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-danger"><i
													class="fas fa-toggle-off"></i></a>
											</c:when>
										</c:choose> <a href="${edit }/${item.productoptions_id }"
										class="btn btn-sm btn-info"><i class="far fa-edit"></i></a> <a
										href="${deltrash }/${item.productoptions_id }"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên sản phẩm</th>
								<th>Nhóm tùy chọn</th>
								<th>Tùy chọn</th>
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
									href="<c:url value="/quan-tri/tuy-chon-san-pham/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/tuy-chon-san-pham/${loop.index}"/>">${loop.index}</a></li>
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