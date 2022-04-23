<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sản phẩm</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="add" value="/quan-tri/san-pham/add" />
		<c:url var="get" value="/quan-tri/san-pham/get" />
		<c:url var="edit" value="/quan-tri/san-pham/edit" />
		<c:url var="onoff" value="/quan-tri/san-pham/status" />
		<c:url var="deltrash" value="/quan-tri/san-pham/trash" />
		<c:url var="trash" value="/quan-tri/san-pham/thung-rac" />
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
							phẩm<c:out value="${mapCate[3]}" />
						</strong>
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
								<th style="width: 200px;">Hình ảnh</th>
								<th>Tên sản phẩm</th>
								<th>Loại sản phẩm</th>
								<th>Số lượng tồn</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ productPaginate }">
								<tr>
									<td>${item.product_id}</td>
									<td><img style="width: 100%;"
										src="${pageContext.request.contextPath}/images/${item.productimg}"></td>
									<td><a title="Chi tiết sản phẩm"
										href="<c:url value='/quan-tri/chi-tiet-san-pham/${item.product_id}'/>">
											${item.productname}</a></td>
									<td>${mapCate[item.product_catid]}</td>
									<td>${item.product_available}</td>
									<td><c:choose>
											<c:when test="${item.product_status==1}">
												<a href="${onoff }/${item.product_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-success"><i
													class="fas fa-toggle-on"></i></a>
											</c:when>
											<c:when test="${item.product_status==2}">
												<a href="${onoff }/${item.product_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-danger"><i
													class="fas fa-toggle-off"></i></a>
											</c:when>
										</c:choose> <a href="${edit }/${item.product_id }"
										class="btn btn-sm btn-info"><i class="far fa-edit"></i></a> <a
										href="${deltrash }/${item.product_id }"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Tên sản phẩm</th>
								<th>Loại sản phẩm</th>
								<th>Số lượng tồn</th>
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
									href="<c:url value="/quan-tri/san-pham/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/san-pham/${loop.index}"/>">${loop.index}</a></li>
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