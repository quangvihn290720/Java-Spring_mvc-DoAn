<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Chi tiết hóa đơn</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<c:url var="list" value="/quan-tri/hoa-don" />
		<c:url var="status" value="/quan-tri/hoa-don/status" />
		<c:url var="cancel" value="/quan-tri/hoa-don/cancel" />
		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card">
				<div class="card-header">
					<h3 class="card-title">
						<strong class="text-danger text-uppercase">Chi tiết đơn
							hàng</strong>
					</h3>
					<div class="card-tools">
						<c:if test="${bill.status != -1 && bill.status != 4}">
							<a class="btn btn-sm btn-danger" href="${cancel }/${bill.id}"
								onclick="return confirm('Bạn có chắc chắn muốn hủy đơn hàng?');">Hủy
								đơn hàng</a>
						</c:if>
						<c:if test="${bill.status != -1 && bill.status != 4}">
							<a class="btn btn-sm btn-info" href="${status }/${bill.id}"
								onclick="return confirm('Bạn có chắc chắn muốn chuyển trạng thái đơn hàng?');">Chuyển
								trạng thái đơn hàng</a>
						</c:if>
						<a class="btn btn-sm btn-danger" href="${list }"><i
							class="fas fa-backward"></i> Quay lại</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Thông tin người nhận hàng</h3>
						</thead>
						<c:if test="${bill.status == -1 }">
							<div class="alert alert-danger" role="alert">Đơn hàng đã
								hủy</div>
						</c:if>
						<c:if test="${bill.status != -1 }">
							<div class="mb-3">
								<ul class="steps">
									<li
										class="step ${bill.status > 0 ? 'step-success': (bill.status == 0 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 4;"><i
												class="fas fa-clipboard"></i></span> <span class="step-text">Đơn
												hàng đã đặt</span>
										</div>
									</li>
									<li
										class="step ${bill.status > 1 ? 'step-success': (bill.status == 1 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 3;"><i
												class="fas fa-clipboard-check"></i></span> <span class="step-text">Đã
												xác nhận đơn hàng</span>
										</div>
									</li>
									<li
										class="step ${bill.status > 2 ? 'step-success': (bill.status == 2 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 2;"><i
												class="fas fa-truck"></i></span> <span class="step-text">Đã
												giao cho ĐVVC</span>
										</div>
									</li>
									<li
										class="step ${bill.status > 3 ? 'step-success': (bill.status == 3 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 1;"><i
												class="fas fa-hourglass-half"></i></span> <span class="step-text">Đơn
												hàng đang giao</span>
										</div>
									</li>
									<li
										class="step ${bill.status > 4 ? 'step-success': (bill.status == 4 ? 'step-active':'' ) }">
										<div class="step-content">
											<span class="step-circle" style="z-index: 0;"><i
												class="far fa-star"></i></span> <span class="step-text">Đơn
												hàng đã giao</span>
										</div>
									</li>
								</ul>
							</div>
						</c:if>
						<tbody>
							<tr>
								<th>Tên:</th>
								<td>${bill.display_name }</td>
							</tr>
							<tr>
								<th>Số điện thoại:</th>
								<td>${bill.phone}</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${bill.email}</td>
							</tr>
							<tr>
								<th>Địa chỉ nhận hàng:</th>
								<td>${bill.address},${bill.province },${bill.district },
									${bill.city }</td>
							</tr>
							<tr>
								<th>Ngày đặt hàng:</th>
								<td>${bill.created_at}</td>
							</tr>
							<tr>
								<th>Phiếu giảm giá:</th>
								<td>${bill.coupon == true?'Có':'Không'}</td>
							</tr>

						</tbody>
					</table>
					<table class="table table-bordered table-hover mb-4">
						<thead>
							<h3>Sản phẩm đã đặt</h3>
						</thead>
						<tbody>
							<tr>
								<th style="width: 350px;">Sản phẩm</th>
								<th style="width: 120px;">Mã sản phẩm</th>
								<th>Giá</th>
								<th>Số lượng</th>
								<th>Tổng</th>
							</tr>

							<c:forEach var="item" items="${ billdetail }">
								<tr>
									<td style="width: 350px;">${item.productinfo.productname }</td>
									<td style="width: 120px;">${item.productinfo.product_id }</td>
									<td><fmt:formatNumber
											value="${item.productinfo.productpricesale}" type="number" />đ</td>
									<td>${item.quanty}</td>
									<td><fmt:formatNumber value="${item.total }" type="number" />đ</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="5" class="text-right pr-5">Phí vận chuyển: <fmt:formatNumber
										value="40000" type="number" />đ
								</td>
							</tr>
							<tr>
								<td colspan="5" class="text-right pr-5">Giảm giá coupon: <fmt:formatNumber
										value="${couponEntity.pricesale}" type="number" />
								</td>
							</tr>
							<tr>
								<td colspan="5" class="text-right pr-5">Tổng cộng: <fmt:formatNumber
										value="${bill.total}" type="number" />đ
								</td>
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