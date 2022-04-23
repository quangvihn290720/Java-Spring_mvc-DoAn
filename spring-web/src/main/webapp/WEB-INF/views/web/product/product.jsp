<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${product.productname}</title>
</head>
<body>

	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
						<li class="breadcrumb-item"><a
							href="<c:url value="/${product.productname}"/>"><span>${mapCate[product.product_catid]}</span></a></li>
						<li class="breadcrumb-item active"><strong><span>${product.productname}</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="shadow-sm row details-product product-bottom">
				<div class="col-lg-12 col-md-12 col-sm-12 col-12 top-product-name">
					<h1 class="title-head">${product.productname}</h1>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-12 col-12 no-padding">
					<div class="product-image-block">
						<div class="slider-for">
							<div class="product1">
								<img
									src="${pageContext.request.contextPath}/images/${product.productimg}"
									width="250px" height="auto">
							</div>
							<c:forEach var="item" items="${listimgprod}">
								<c:if test="${product.product_id == item.product_id }">
									<div class="product1">
										<img
											src="${pageContext.request.contextPath}/images/${item.img}"
											width="250px" height="auto">
									</div>
								</c:if>
							</c:forEach>
						</div>
						<div class="slider-nav">
							<div class="product2" style="width: 69.2px; margin-right: 10px;">
								<img
									src="${pageContext.request.contextPath}/images/${product.productimg}"
									width="250px" height="auto">
							</div>
							<c:forEach var="item" items="${listimgprod}">
								<c:if test="${product.product_id == item.product_id }">
									<div class="product2"
										style="width: 69.2px; margin-right: 10px;">
										<img
											src="${pageContext.request.contextPath}/images/${item.img}">
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-lg-5 col-md-6 col-sm-12 col-12 details-pro">
					<div class="product-top clearfix">
						<div class="sku-product clearfix">
							<span>Thương hiệu: <strong>${mapManu[product.manufacturer_id]}</strong></span>
							<span class="code">Mã sản phẩm: <strong>(Đang cập
									nhật...)</strong>
							</span>
						</div>
						<div class="price-box clearfix">
							<span class="special-price"><fmt:formatNumber
									value="${product.productpricesale}" type="number" /> ₫</span><span
								class="old-price">Giá niêm yết: <del
									class="product-price-old">
									<fmt:formatNumber value="${product.productprice}" type="number" />
									₫
								</del>

							</span> <span class="save-price"> Tiết kiệm: <span
								class="product-price-save"><fmt:formatNumber
										value="${product.productprice - product.productpricesale}"
										type="number" />₫</span>
							</span>
						</div>
						<div class="form-product">
							<form enctype="multipart/form-data" id="add-to-cart-form"
								method="get" class="clearfix has-validation-callback"
								action="<c:url value="/AddCart/${product.product_id}"/>">
								<div class="btn-mua">
									<button type="submit"
										class="btn btn-lg btn-gray btn-cart btn_buy add_to_cart">
										Mua ngay <span>Giao tận nơi hoặc nhận tại cửa hàng</span>
									</button>
								</div>
							</form>
							<div class="product-wish">
								<button
									class="button-bottom-form 
									favorites-btn js-btn-wishlist js-favorites js-favorites-heart">
									Mua trả góp 0%<span>Duyệt hồ sơ qua siêu thị</span>
								</button>
								<a href="" class="button-bottom-form" title="Trả góp">Trả
									góp 0% qua thẻ<span>Visa, Master, JCB</span>
								</a>
							</div>
							<div class="mt-2">
								<div class="fb-like"
									data-href="http://localhost:8080/spring-mvc-doan/chi-tiet-san-pham/${product.product_slug}"
									data-width="" data-layout="standard" data-action="like"
									data-size="large" data-share="true"></div>
							</div>
							<div class="product-hotline">
								Gọi <a href="tel:${configweb.hotline}"
									title="${configweb.hotline}">${configweb.hotline}</a> để tư vấn
								mua hàng
							</div>
						</div>
					</div>
				</div>
				<div
					class="col-lg-3 col-md-12 col-sm-12 col-12 shop-feature no-padding">
					<div class="product-summary">
						<p>
							<b>Tình trạng</b><br>${product.product_condition}
						</p>
						<p>
							<b>Bảo hành</b><br>${product.product_guarantee}
						</p>
					</div>
					<div class="product-promotion">
						<c:forEach var="item" items="${listsmartpay}">
							<div class="promotion-item">
								<img src="${pageContext.request.contextPath}/images/${item.img}">
								<div class="text">
									<strong>${item.name}</strong>${item.metadesc}
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-lg-7" style="padding-left: 0 !important;">
					<div class="product-details shadow-sm">
						<div class="title">Đánh giá chi tiết ${product.productname}</div>
						<div class="product-content">${product.productdetail}</div>
					</div>
				</div>
				<div class="col-lg-5 product-fix-padding">
					<div class="shadow-sm specifications">
						<div class="title">Thông số kỹ thuật</div>
						<table id="tskt">
							<tbody>
								<tr>
									<td>Hãng sản xuất</td>
									<td>${mapManu[product.manufacturer_id]}</td>
								</tr>
								<tr>
									<td>Kích thước</td>
									<td>${product.productweight}</td>
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

															<td>${itemoptiongroup.optiongroupname}</td>

															<td>${itemoption.optionname}</td>

														</tr>
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach>
									</c:if>
								</c:forEach>
								<tr>
									<td>Tiện ích</td>
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
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="fb-comments"
				data-href="http://localhost:8080/spring-mvc-doan/chi-tiet-san-pham/${product.product_slug}"
				data-width="" data-numposts="5"></div>
		</div>
	</section>
	<section class="clearfix">
		<div class="container">
			<div class="relate-product shadow-sm row">
				<div class="col-lg-12">
					<div class="module-header">
						<h1 class="title-head module-title border-title sidebar-title-1">
							Sản phẩm liên quan</h1>
						<div class="owl-carousel owl-theme">
							<c:set var="countList" value="${ listproduct.size() }" />
							<c:forEach var="item" items="${ listproduct }" begin="0"
								end="${countList}" varStatus="loop">
								<c:if
									test="${item.product_catid == product.product_catid && item.product_id != product.product_id}">
									<div class="item">
										<div class="card pt-3" style="width: 100%;">
											<a
												href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
												title="${item.productname}"> <img class="card-img-top"
												src="${pageContext.request.contextPath}/images/${item.productimg}"
												alt="" height="175">
											</a>
											<div class="card-body text-center">
												<h6 class="card-title" title="${item.productname}">
													<a class="product-item-name"
														href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
														title="${item.productname}">${item.productname}</a>
												</h6>
												<p>
													<span class="price"><fmt:formatNumber
															value="${item.productpricesale}" type="number" />đ</span><span
														class="oldprice"><fmt:formatNumber
															value="${item.productprice}" type="number" />đ</span>
												</p>
												<div class="group-action">
													<a class="btn-buy" title="Chi tiết sản phẩm"
														href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>">
														<i class="fas fa-search"></i>
													</a> <a class="btn-detail" title="Thêm vào giỏ hàng"
														data-id="${item.product_id}"
														href="<c:url value="/AddCart/${item.product_id}"/>"> <i
														class="fas fa-cart-plus"></i></a>
												</div>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(function() {
					$("#slider").slick({
						autoplay : true,
						speed : 1000,
						arrows : false,
						asNavFor : "#thumbnail_slider"
					});
					$("#thumbnail_slider").slick({
						slidesToShow : 3,
						speed : 1000,
						asNavFor : "#slider"
					});
				});
			</script>
			<script type="text/javascript">
				$(function() {
					$(document)
							.ready(
									function() {
										$('.owl-carousel')
												.owlCarousel(
														{
															loop : false,
															margin : 10,
															nav : true,
															navText : [
																	"<i class='fa fa-angle-left' aria-hidden='true'></i>",
																	"<i class='fa fa-angle-right' aria-hidden='true'></i>" ],
															dots : false,
															responsive : {
																0 : {
																	items : 2
																},
																600 : {
																	items : 3
																},
																1000 : {
																	items : 5
																}
															}
														})
									});
				});
			</script>
			<script type="text/javascript">
				$(document).ready(function() {
					$('.slider-for').slick({
						slidesToShow : 1,
						slidesToScroll : 1,
						arrows : false,
						accessibility : false,
						swipe : false,
						asNavFor : '.slider-nav'
					});
					$('.slider-nav').slick({
						slidesToShow : 4,
						slidesToScroll : 1,
						asNavFor : '.slider-for',
						dots : false,
						infinite : false,
						swipe : true,
						focusOnSelect : true,
						responsive : [ {
							breakpoint : 1024,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								infinite : false,
								dots : false,
							}
						}, {
							breakpoint : 600,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								dots : false
							}
						}, {
							breakpoint : 480,
							settings : {
								slidesToShow : 4,
								slidesToScroll : 1,
								dots : false
							}
						}
						// You can unslick at a given breakpoint now by adding:
						// settings: "unslick"
						// instead of a settings object
						]
					});
				});
			</script>
		</div>
	</section>
	<script>
		toastr.options = {
			"closeButton" : true,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "0",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};
		$(document).ready(function onDocumentReady() {
			var msgAddCart = '${msgAddCart}';
			console.log(msgAddCart);
			if (msgAddCart) {
				toastr.success(msgAddCart);
			}
		});
	</script>
	<script>
		$(".btn-detail").on("click", function(e) {
			var id = $(this).data("id");
			e.preventDefault();
			$.ajax({
				url : '${pageContext.request.contextPath}/AddCart/' + id,
				type : 'get',
				success : function(result) {
					var carttotal = $("#cart-total").text();
					$("#cart-total").text(parseInt(carttotal) + 1);
					toastr.success("Thêm vào giỏ hàng thành công");
				},
				error : function(request, status, error) {
					toastr.error("Có lỗi khi thêm vào giỏ hàng");
				}
			});
		});
	</script>
</body>
</html>