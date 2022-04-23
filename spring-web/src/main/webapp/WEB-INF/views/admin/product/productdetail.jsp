<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="list" value="/quan-tri/san-pham" />
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
						<strong class="text-danger text-uppercase">Chi tiết sản
							phẩm</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-danger" href="${list }"><i
							class="fas fa-backward"></i> Quay lại</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin sản phẩm</h3>
						</thead>
						<tbody>
							<tr>
								<th>Tên sản phẩm:</th>
								<td>${product.productname}</td>
							</tr>
							<tr>
								<th>Slug:</th>
								<td>${product.product_slug}</td>
							</tr>
							<tr>
								<th>Giá gốc:</th>
								<td><fmt:formatNumber value="${product.productprice}"
										type="number" /> đ</td>
							</tr>
							<tr>
								<th>Giá giảm:</th>
								<td><fmt:formatNumber value="${product.productpricesale}"
										type="number" /> đ</td>
							</tr>
							<tr>
								<th>Cân nặng sản phẩm:</th>
								<td>${product.productweight}kg</td>
							</tr>
							<tr>
								<th>Loại sản phẩm:</th>
								<td>${mapCate[product.product_catid]}</td>
							</tr>
							<tr>
								<th>Nhà sản xuất:</th>
								<td>${mapManu[product.manufacturer_id]}</td>
							</tr>
							<tr>
								<th>Ngày tạo sản phẩm:</th>
								<td>${product.created_at}</td>
							</tr>
							<tr>
								<th>Mô tả ngắn sản phẩm:</th>
								<td>${product.productshortdesc}</td>
							</tr>
							<tr>
								<th >Mô tả sản phẩm:</th>
								<td class="detail-prodetail">${product.productdetail}</td>
							</tr>
							<tr>
								<th>Tiện ích <a
									href="<c:url value="/quan-tri/san-pham/them-tien-ich/${product.product_id}"/>">(Thêm)</a></th>
								<td><c:if test="${listutilities.size()>0}">
										<c:forEach var="item" items="${listutilities}">
											<c:if test="${item.product_id == product.product_id}">
												<li>${item.utilitiesname}</li>
											</c:if>
										</c:forEach>
									</c:if> <c:if test="${empty listutilities}">
										<p></p>
									</c:if></td>
							</tr>
							<c:forEach var="item" items="${listprodoption}">
								<c:if test="${product.product_id == item.product_id}">
									<c:forEach var="itemoption" items="${listoption}">
										<c:if
											test="${item.optiongroup_id == itemoption.optiongroup_id && item.option_id == itemoption.options_id}">
											<c:forEach var="itemoptiongroup" items="${listoptiongroup}">
												<c:if
													test="${item.optiongroup_id == itemoptiongroup.optiongroups_id && itemoption.optiongroup_id == itemoptiongroup.optiongroups_id }">
													<tr>

														<th>${itemoptiongroup.optiongroupname}</th>

														<td>${itemoption.optionname}</td>

													</tr>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
							<tr>
								<th>Thêm lựa chọn</th>
								<td><a
									href="<c:url value="/quan-tri/san-pham/them-lua-chon/${product.product_id}"/>">Thêm</a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.card-body -->
				<div class="card-footer">Footer</div>
				<!-- /.card-footer-->
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
</body>
</html>