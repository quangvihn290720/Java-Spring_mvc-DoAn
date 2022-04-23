<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Xác nhận đơn hàng</title>
<!-- Required meta tags -->

</head>
<body
	style="display: flex; flex: 1 0 auto; flex-direction: column; background-color: #e6e8ea">
	<header class="banner">
		<div class="wrap">
			<div class="logo-center">
				<h1 class="shop_name">${configweb.nameweb}</h1>
			</div>
		</div>
	</header>
	<section class=" section-thank-mobile banner">
		<div class="section-thank-icon">
			<svg xmlns="http://www.w3.org/2000/svg" width="72px" height="72px">
				<g fill="none" stroke="#8EC343" stroke-width="2">
					<circle cx="36" cy="36" r="35"
					style="stroke-dasharray:240px, 240px; stroke-dashoffset: 480px;"></circle>
					<path d="M17.417,37.778l9.93,9.909l25.444-25.393"
					style="stroke-dasharray:50px, 50px; stroke-dashoffset: 0px;"></path>
				</g>
			</svg>
		</div>
		<div class="thank-message-container">
			<h2 class="section-title">Cảm ơn bạn đã đặt hàng</h2>

			<p class="section-text">
				Một email xác nhận đã được gửi tới ${bill.email}. <br /> Xin vui
				lòng kiểm tra email của bạn
			</p>
		</div>
	</section>
	<aside>
		<button class="order-summary-toggle" data-toggle="#order-summary"
			data-toggle-class="order-summary--is-collapsed">
			<span class="wrap"> <span class="order-summary-toggle__inner">
					<span class="order-summary-toggle__text expandable"> Đơn
						hàng (${billdetail.size()} sản phẩm) </span>
			</span>
			</span>
		</button>
	</aside>
	<div class="content">
		<form id="checkoutForm" action="asdas" method="post">
			<div class="wrap">
				<div class="main-co">
					<div class="main-co-header">
						<div class="logo logo-center">
							<h1 class="shop_name">${configweb.nameweb}</h1>
						</div>
						<section class="section-thank">
							<div class="section-thank-icon">
								<svg xmlns="http://www.w3.org/2000/svg" width="72px"
									height="72px">
											<g fill="none" stroke="#8EC343" stroke-width="2">
												<circle cx="36" cy="36" r="35"
										style="stroke-dasharray:240px, 240px; stroke-dashoffset: 480px;"></circle>
												<path d="M17.417,37.778l9.93,9.909l25.444-25.393"
										style="stroke-dasharray:50px, 50px; stroke-dashoffset: 0px;"></path>
											</g>
										</svg>
							</div>
							<div class="thank-message-container">
								<h2 class="section-title">Cảm ơn bạn đã đặt hàng</h2>

								<p class="section-text">
									Một email xác nhận đã được gửi tới ${bill.email}. <br /> Xin
									vui lòng kiểm tra email của bạn
								</p>
							</div>
						</section>
					</div>
					<div class="main-co-content">
						<article class=" row">
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title layout-flex-item ">Thông tin
												mua hàng</h2>
										</div>
									</div>
									<p>${bill.display_name}</p>
									<p>${bill.email}</p>
									<p>${bill.phone}</p>
								</section>
							</div>
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title">Địa chỉ nhận hàng</h2>
										</div>
									</div>
									<p>${bill.address}</p>
									<p>${bill.province},${bill.district},${bill.city}</p>
									<p>${bill.phone}</p>
								</section>
							</div>
						</article>
						<article class=" row">
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title layout-flex-item ">Phương
												thức thanh toán</h2>
										</div>
									</div>
									Thanh toán khi giao hàng (COD)
								</section>
							</div>
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title">Phương thức vận chuyển</h2>
										</div>
									</div>
									Giao hàng tận nơi
								</section>

							</div>
						</article>
						<div
							class="field__input-btn-wrapper field__input-btn-wrapper-vertical"
							style="margin-top: 7em;">
							<a href="<c:url value="/"/>" type="submit"
								class="btn btn-dark btn-checkout spinner"> <span
								class="spinner-label">Tiếp tục mua hàng</span>
							</a>
							<!--<a href="/cart" class="print-link"> <i class="fa fa-print"></i>
								<span class="previous-link__content">In</span>
							</a> -->
						</div>
					</div>
				</div>
				<aside class="sidebar-co">
					<div class="sidebar-co-header">
						<h2 class="sidebar-co-title">Đơn hàng (${billdetail.size()})</h2>
					</div>
					<div class="sidebar-co-content">
						<div id="order-summary"
							class="order-summary order-summary--is-collapsed">
							<div class="order-summary-sections">
								<div
									class="order-summary__section order-summary__section--product-list order-summary-scroll order-summary--collapse-element">
									<table class="product-table">
										<thead class="product-table-header">
											<tr>
												<th><span class="visually-hidden">Ảnh sản phẩm</span></th>
												<th><span class="visually-hidden">Mô tả</span></th>
												<th><span class="visually-hidden">Sổ lượng</span></th>
												<th><span class="visually-hidden">Đơn giá</span></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${billdetail}">
												<tr class="product">
													<td class="product__image">
														<div class="product-thumbnail">
															<div class="product-thumbnail__wrapper" data-tg-static>

																<img
																	src="${pageContext.request.contextPath}/images/${item.productinfo.productimg}"
																	alt="" class="product-thumbnail__image">

															</div>
															<span class="product-thumbnail__quantity">${item.quanty}</span>
														</div>
													</td>
													<th class="product__description"><span
														class="product__description__name">${item.productinfo.productname}
													</span></th>
													<td class="product__quantity visually-hidden"><em>Số
															lượng:</em>${item.quanty}</td>
													<td class="product__price"><fmt:formatNumber
															value="${item.productinfo.productpricesale}"
															type="number" />đ</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div
									class="order-summary__section order-summary__section--total-lines"
									id="orderSummary">
									<table class="total-line-table">
										<caption class="visually-hidden">Tổng giá trị</caption>
										<thead>
											<tr>
												<td><span class="visually-hidden">Mô tả</span></td>
												<td><span class="visually-hidden">Giá tiền</span></td>
											</tr>
										</thead>
										<tbody class="total-line-table__tbody">
											<tr class="total-line total-line--subtotal">
												<th class="total-line__name">Tạm tính</th>
												<td class="total-line__price"><fmt:formatNumber
														value="${total}" type="number" />đ</td>
											</tr>
											<tr class="total-line total-line--shipping-fee">
												<th class="total-line__name">Phí vận chuyển</th>
												<td class="total-line__price">40.000đ</td>
											</tr>
											<tr class="total-line total-line--coupon">
												<th class="total-line__name">Mã khuyến mãi</th>
												<td class="total-line__price"><fmt:formatNumber
														value="${bill.coupon?-couponEntity.pricesale:0}"
														type="number" />đ</td>
											</tr>
										</tbody>
										<tfoot class="total-line-table__footer">
											<tr class="total-line payment-due">
												<th class="total-line__name"><span
													class="payment-due__label-total"> Tổng cộng </span></th>
												<td class="total-line__price"><span
													class="payment-due-price"><fmt:formatNumber
															value="${bill.total}  " type="number" />đ</span></td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</div>
					</div>
				</aside>
			</div>
		</form>
	</div>
</body>