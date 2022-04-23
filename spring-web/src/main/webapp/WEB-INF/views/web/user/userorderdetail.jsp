<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đơn hàng</title>
</head>
<body>
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="<c:url value='/'/>"><span>Trang
									chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Trang
									khách hàng</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="container">
			<div class="row py-3 ">
				<div class="col-xs-12 col-sm-12 col-lg-3 col-left-ac">
					<div class="left-account">
						<h5 class="title-account">Thông tin tài khoản</h5>
						<p>Xin chào, ${LoginInfo.user_fullname}!</p>
						<ul>
							<li><a href="<c:url value='/tai-khoan'/>" class="title-info">Thông
									tin tài khoản</a></li>
							<li><a href="<c:url value='/tai-khoan/don-hang'/>"
								class="title-info">Đơn hàng của bạn</a></li>
							<li><a href="<c:url value='/tai-khoan/doi-mat-khau'/>"
								class="title-info">Đổi mật khẩu</a></li>
							<li><a href="<c:url value='/dang-xuat'/>" class="title-info">Đăng
									xuất</a></li>
						</ul>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-lg-9 col-right-ac">
					<div class="row">
						<div class="col-9">
							<h1 class="title-head">Đơn hàng của bạn</h1>


						</div>
						<div class="col-3" style="position: relative;">
							<a href="<c:url value="/tai-khoan/don-hang"/>" type="button"
								class="btn btn-secondary"
								style="position: absolute; right: 14px;"> <i
								class="fas fa-arrow-left"></i> Quay về
							</a>

						</div>
					</div>
					<c:if test="${bill.status == -1 }">
						<div class="alert alert-danger" role="alert">Đơn hàng đã hủy</div>
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
										<span class="step-circle" style="z-index: 2;"
											style="z-index:3;"><i class="fas fa-truck"></i></span> <span
											class="step-text">Đã giao cho ĐVVC</span>
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
					<div class="recent-orders">
						<div class="table-responsive">
							<div class="card-body">
								<table class="table table-bordered table-hover mb-4">
									<thead>
										<div class="row">
											<div class="col-7">

												<h3>Người nhận</h3>
											</div>
											<div class="col-5" style="position: relative;">
												<c:if test="${bill.status == 0 }">
													<a
														href="<c:url value='/tai-khoan/don-hang/cancel'/>/${bill.id}"
														onclick="return confirm('Bạn có chắc chắn muốn hủy đơn hàng?');"
														type="button" class="btn btn-secondary"
														style="position: absolute; right: 14px;">Hủy đơn hàng</a>
												</c:if>
											</div>
									</thead>
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
												<td><fmt:formatNumber value="${item.total }"
														type="number" />đ</td>
											</tr>
										</c:forEach>
										<tr>
											<td colspan="5" class="text-right pr-5">Phí vận chuyển:
												<fmt:formatNumber value="40000" type="number" />đ
											</td>
										</tr>
										<tr>
											<td colspan="5" class="text-right pr-5">Giảm giá coupon:
												<fmt:formatNumber value="${couponEntity.pricesale}"
													type="number" />đ
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>