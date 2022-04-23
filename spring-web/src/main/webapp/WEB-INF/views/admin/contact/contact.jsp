<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="get" value="/quan-tri/lien-he/edit" />
		<c:url var="onoff" value="/quan-tri/lien-he/status" />
		<c:url var="list" value="/quan-tri/lien-he" />
		<c:url var="detail" value="/quan-tri/lien-he/chi-tiet" />
		<c:url var="delete" value="/quan-tri/lien-he/delete" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-uppercase text-danger">Danh sách liên
							hệ</strong>
					</h3>
					<div class="card-tools"></div>
				</div>
				<div class="card-body">
					<c:if test="${not empty exception3}">
						<div class="alert alert-success">${ msgSavess}</div>
					</c:if>
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên người liên hệ</th>
								<th>Chủ đề</th>
								<th>Tình trạng</th>
								<th>Chi tiết</th>
								<th>Xóa</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${contactPaginate.size()>0}">
								<c:forEach var="item" items="${ contactPaginate }">
									<tr>
										<td>${item.id}</td>
										<td>${item.name}</td>
										<td>${item.subject}</td>
										<td><c:choose>
												<c:when test="${item.status == 1}">
													<a href="${onoff}/${item.id}"
														onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
														class="btn btn-sm btn-danger ">Chưa phản hồi</a>
												</c:when>
												<c:otherwise>
													<a href="" class="btn btn-sm btn-info">Đã
														phản hồi</a>
												</c:otherwise>
											</c:choose></td>
										<td><a href="${detail}/${item.id}">Chi tiết</a></td>
										<td><a href="${delete}/${item.id}"
											onclick="return confirm('Bạn có chắc chắn thực hiện không?');">Xóa</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 20px;" class="text-center">ID</th>
								<th style="width: 200px;">Tên người liên hệ</th>
								<th>Chủ đề</th>
								<th>Tình trạng</th>
								<th>Chi tiết</th>
								<th>Xóa</th>
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
									href="<c:url value="/quan-tri/lien-he/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/lien-he/${loop.index}"/>">${loop.index}</a></li>
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