<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
</head>
<body>
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="<c:url value="/trang-chu"/>"><span>Trang
									chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Giỏ
									hàng</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<c:choose>
		<c:when test="${Cart.size() > 0}">
			<section class="cart-template clearfix">
				<div class="main-cart-page">
					<div class="container">
						<div class="container">
							<c:if test="${not empty msgDeleteCart}">
								<div class="alert alert-danger">${ msgDeleteCart}</div>
							</c:if>
							<c:if test="${not empty msgUpdateCart}">
								<div class="alert alert-success">${ msgUpdateCart}</div>
							</c:if>
						</div>
						<div class="cart_desktop_page cart-page">
							<div class="cart page_cart hidden-xs">
								<form action="/checkout" method="get">
									<div class="bg-scroll">
										<div class="cart-thead">
											<div style="width: 17%">Ảnh sản phẩm</div>
											<div style="width: 33%">Tên sản phẩm</div>
											<div style="width: 15%">Đơn giá</div>
											<div style="width: 14%">Số lượng</div>
											<div style="width: 15%">Thành tiền</div>
											<div style="width: 6%">Xóa</div>
										</div>
										<div class="cart-tbody">
											<c:forEach var="item" items="${ Cart }" varStatus="index">
												<div class="item-cart">
													<div style="width: 17%" class="image">
														<a class="product-image" title="${item.value.productEntity.productimg}" href="<c:url value='/chi-tiet-san-pham/${item.value.productEntity.product_slug}'/>"><img
															src="${pageContext.request.contextPath}/images/${item.value.productEntity.productimg}"
															alt="${item.value.productEntity.productname}"></a>
													</div>
													<div style="width: 33%">
														<h3 class="product-name" title="${item.value.productEntity.productname}">
															<a href="<c:url value='/chi-tiet-san-pham/${item.value.productEntity.product_slug}'/>">${item.value.productEntity.productname}</a>
														</h3>
													</div>
													<div style="width: 15%">
														<span class="item-price"><span class="price"><fmt:formatNumber
																	value="${item.value.productEntity.productpricesale}"
																	type="number" />₫</span></span>
													</div>
													<div style="width: 14%">
														<div class="input-qty">
															<button data-id="${item.key}"
																class="reduced_pop items-count btn-minus edit-cart-down"
																type="button">-</button>
															<input type="text" maxlength="3" min="0"
																id="" class="number-sidebar quanty-cart-${item.key}"
																size="4" value="${item.value.quanty}">
															<button data-id="${item.key}"
																class="increase_pop items-count btn-plus edit-cart-up"
																type="button">+</button>
														</div>
													</div>
													<div style="width: 15%">
														<span class="cart-price"><span class="price"><fmt:formatNumber
																	value="${item.value.quanty * item.value.productEntity.productpricesale}"
																	type="number" />₫</span></span>
													</div>
													<div style="width: 6%">
														<a class="far fa-trash-alt"
															href="<c:url value="/DeleteCart/${item.key}"/>"></a>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</form>
								<div class="cart-collaterals cart_submit row">
									<div class="totals col-sm-12 col-md-12 col-xs-12">
										<div class="totals">
											<div class="inner">
												<table class="table shopping-cart-table-total"
													id="shopping-cart-totals-table">
													<colgroup>
														<col>
														<col>
													</colgroup>
													<tfoot>
														<tr>
															<td colspan="20" class="a-right"><span>Tổng
																	sản phẩm</span></td>
															<td class="a-right"><strong> <span>${ TotalQuantyCart }</span>
															</strong></td>
														</tr>
														<tr>
															<td colspan="20" class="a-right"><span>Tổng
																	tiền</span></td>
															<td class="a-right"><strong> <span
																	class="totals_price price"><fmt:formatNumber
																			value="${ TotalPriceCart }" type="number" />₫</span>
															</strong></td>
														</tr>
													</tfoot>
												</table>
												<ul class="checkout">
													<li class="clearfix"><a
														href="<c:url value='/trang-chu'/>"
														class="btn btn-white f-left" title="Tiếp tục mua hàng"
														type="button"> <span> Tiếp tục mua hàng</span>
													</a> <a class="btn btn-white f-left"
														href="<c:url value='/deletecart'/>"
														title="Xóa toàn bộ đơn hàng" type="button"> <span>
																Xóa toàn bộ đơn hàng</span>
													</a> <a
														class="btn btn-primary button btn-proceed-checkout float-right"
														title="Tiến hành đặt hàng"
														href="<c:url value='/checkout'/>"> <span> Tiến
																hành đặt hàng</span>
													</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="cart-mobile hidden-md hidden-lg hidden-sm">
						<form action="/checkout" method="get">
							<div>
								<div class="header-cart" style="background: #fff;">
									<div class="title-cart">
										<h3>Giỏ hàng của bạn (${ TotalQuantyCart })</h3>
									</div>
								</div>
								<div class="header-cart-content">
									<div class="cart_page_mobile content-product-list">
										<c:forEach var="item" items="${ Cart }" varStatus="index">
											<div class="item-product">
												<div class="item-product-cart-mobile">
													<a href="<c:url value='/chi-tiet-san-pham/${item.value.productEntity.product_slug}'/>" class="product-images1" title="${item.value.productEntity.productimg}">
														<img
														src="${pageContext.request.contextPath}/images/${item.value.productEntity.productimg}"
														width="80" height="150" alt="${item.value.productEntity.productimg}">
													</a>
												</div>
												<div class="title-product-cart-mobile">
													<h3>
														<a href="<c:url value='/chi-tiet-san-pham/${item.value.productEntity.product_slug}'/>" title="${item.value.productEntity.productname}">${item.value.productEntity.productname}</a>
													</h3>
													<p>
														Giá: <span class="price"><fmt:formatNumber
																value="${item.value.productEntity.productpricesale}"
																type="number" />₫</span>
													</p>
												</div>
												<div class="select-item-qty-mobile">
													<div class="txt_center">
														<button data-id="${item.key}"
															class="reduced items-count btn-minus edit-cart-down"
															type="button">-</button>
														<input type="text" maxlength="3" min="0"
															id="" class="number-sidebar quanty-cart-${item.key}"
															size="4" value="${item.value.quanty}">
														<button data-id="${item.key}" class="increase items-count btn-plus edit-cart-up"
															type="button">+</button>
													</div>
													<a class="button remove-item remove-item-cart"
														href="<c:url value="/DeleteCart/${item.key}"/>">Xoá</a>
												</div>
											</div>
										</c:forEach>
									</div>
									<div class="header-cart-price">
										<div class="title-cart">
											<h3>Tổng tiền</h3>
											<a class="text-xs-right totals_price_mobile"><fmt:formatNumber
													value="${ TotalPriceCart }" type="number" />₫</a>

										</div>
										<div class="title-cart">
											<h3>Tổng sản phẩm</h3>
											<a class="text-xs-right totals_price_mobile">${ TotalQuantyCart }</a>

										</div>
										<div class="checkout">
											<a class="btn btn-info btn-proceed-checkout-mobile"
												href="<c:url value='/checkout'/>">Tiến hành thanh toán</a>

										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</section>
		</c:when>
		<c:otherwise>
			<div class="container">
				<img width="100%"
					src="<c:url value="/template/web/assets/images/empty-cart.png"/>">
			</div>
		</c:otherwise>
	</c:choose>
	<content tag="script"> <script>
		$(".edit-cart-down").on("click", function() {
			var id = $(this).data("id");
			var quanty = $(".quanty-cart-" + id).val();
			quanty--;
			if (quanty == 0)
				return quanty;
			//window.location = "EditCart/" + id + "/" + quanty;
			$.ajax
		    ({ 
		        url: "EditCart/" + id + "/" + quanty,
		        type: 'post',
		        success: function(result)
		        {
		        	location.reload();
		        }
		    });
		});
		$(".edit-cart-up").on("click", function() {
			var id = $(this).data("id");
			var quanty = $(".quanty-cart-" + id).val();
			quanty++;
			//window.location = "EditCart/" + id + "/" + quanty;
			$.ajax
		    ({ 
		        url: "EditCart/" + id + "/" + quanty,
		        type: 'post',
		        success: function(result)
		        {
		        	location.reload();
		        }
		    });
		});
	</script> </content>
</body>
</html>