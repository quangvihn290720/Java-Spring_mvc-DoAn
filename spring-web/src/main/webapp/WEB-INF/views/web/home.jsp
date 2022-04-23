<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<section class="clearfix mt-3">
		<div class="container section-slider">
			<div class="row">
				<div class="col-xl-3 col-lg-3 width-cate d-lg-block d-none"">
					<ul class="main-cate">
						<c:forEach var="item" items="${listcate}" varStatus="index">
							<li class="main-cate-has-child menu-item-count"><a
								href="<c:url value="/${item.slug}?page=1"/>"
								title="${ item.name }">${ item.name }</a>
								<ul class="menu-child sub-menu sub-mega-menu">
									<li class="main-cate-has-child clearfix"><a
										href="<c:url value="/${item.slug}?page=1"/>"
										title="${ item.name }">${ item.name }</a>
										<ul class="menu-child-2 sub-menu clearfix">
											<c:forEach var="itemprod" items="${listproductmenu}">
												<c:if test="${itemprod.product_catid == item.id}">
													<c:forEach var="itemmanu" items="${listmanu}">
														<c:if
															test="${itemmanu.manufacturer_id == itemprod.manufacturer_id}">
															<li><a
																href="<c:url value="/${item.slug}-${itemmanu.manufacturer_slug}?page=1"/>"
																title="${itemmanu.manufacturer_name}">${itemmanu.manufacturer_name}</a></li>
														</c:if>
													</c:forEach>
												</c:if>

											</c:forEach>

										</ul></li>
									<!-- <li class="main-cate-has-child clearfix"><a href="#"
										title="Apple">Apple</a>
										<ul class="menu-child-2 sub-menu clearfix">
											<li><a href="/iphone-12-series" title="iPhone 12 Series">iPhone
													12 Series </a></li>
											<li><a href="/iphone-11-series" title="iPhone 11 Series">iPhone
													11 Series </a></li>
											<li><a href="/iphone-11-series" title="iPhone 11 Series">iPhone
													11 Series </a></li>
											<li><a href="/iphone-11-series" title="iPhone 11 Series">iPhone
													11 Series </a></li>
											<li><a href="/iphone-11-series" title="iPhone 11 Series">iPhone
													11 Series </a></li>
										</ul></li> -->
								</ul></li>
						</c:forEach>
					</ul>
				</div>
				<script type="text/javascript">
					var menu_limit = "8";
					if (isNaN(menu_limit)) {
						menu_limit = 10;
					} else {
						menu_limit = 8;
					}
					var sidebar_length = $('.menu-item-count').length;
					if (sidebar_length > (menu_limit + 1)) {
						$('.width-cate > ul')
								.each(
										function() {
											$('.menu-item-count', this).eq(
													menu_limit).nextAll()
													.hide().addClass(
															'toggleable');
											$(this)
													.append(
															'<li class="more"><a title="Xem thêm...">Xem thêm...</a></li>');
										});
						$('.width-cate > ul')
								.on(
										'click',
										'.more',
										function() {
											if ($(this).hasClass('less')) {
												$(this)
														.html(
																'<a title="Xem thêm...">Xem thêm...</a>')
														.removeClass('less');
											} else {
												$(this)
														.html(
																'<a title="Thu gọn...">Thu gọn...</a>')
														.addClass('less');
												;
											}
											$(this)
													.siblings('li.toggleable')
													.slideToggle(
															{
																complete : function() {
																	var divHeight = $(
																			'.main-cate')
																			.height();
																}
															});
										});
					}
				</script>
				<div class="col-xl-9 col-lg-9 home-sliders">
					<div id="carouselExampleIndicators" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carouselExampleIndicators" data-slide-to="0"
								class="active"></li>
							<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						</ol>
						<div class="carousel-inner">
							<c:forEach var="item" items="${ slides }" varStatus="index">
								<c:if test="${ index.first }">
									<div class="carousel-item active">
										<img class="d-block w-100"
											src="${pageContext.request.contextPath}/images/${item.slide_img}"
											alt="${item.slide_caption}">
									</div>
								</c:if>
								<c:if test="${ not index.first }">
									<div class="carousel-item">
										<img class="d-block w-100"
											src="${pageContext.request.contextPath}/images/${item.slide_img}"
											alt="${item.slide_caption}">
									</div>
								</c:if>
							</c:forEach>
						</div>
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="banner2 clearfix mt-3">
		<div class="container">
			<div class="row">
				<c:forEach var="item" items="${listbanner}" begin="0" end="1">
					<div class="col-md-6  mb-3 pr-3">
						<img
							src="${pageContext.request.contextPath}/images/${item.banner_img}"
							alt="${item.banner_name}" height="auto" width="100%">
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- sp-->
	<section class="clearfix">
		<div class="container ">
			<div class="block-home-content">
				<div class="title">
					<a class="title-content text-bold" href="##"><span>Sản
							phẩm mới</span></a>
				</div>
				<div class="row">
					<c:if test="${ listprodnew.size() > 0 }">
						<c:set var="countList" value="7" />
						<c:forEach var="item" items="${listprodnew}" begin="0"
							end="${ countList }" varStatus="indexs">
							<div class="col-6 col-sm-6 col-md-3 mt-3 ">
								<div class="card pt-3" style="width: 100%;">
									<a
										href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
										title="${item.productname}"> <img class="card-img-top"
										src="${pageContext.request.contextPath}/images/${item.productimg}"
										alt="" height="175"">
									</a>
									<div class="card-body text-center">
										<h6 class="card-title" title="${item.productname}">
											<a class="product-item-name"
												href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
												title="${item.productname}">${item.productname} </a>
										</h6>
										<p>
											<span class="price"><fmt:formatNumber
													value="${item.productpricesale}" type="number" />đ</span><span
												class="oldprice"><fmt:formatNumber
													value="${item.productprice}" type="number" />đ</span>
										</p>
										<div class="group-action">
											<a class="btn-buy" title="Chi tiết sản phẩm"
												href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/> ">
												<i class="fas fa-search"></i>
											</a> <a class="btn-detail" title="Thêm vào giỏ hàng"
												data-id="${item.product_id}"
												href="<c:url value="/AddCart/${item.product_id}"/>"><i
												class="fas fa-cart-plus"></i></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<section class="clearfix mt-4">
		<div class="container ">
			<div class="block-home-content">
				<div class="title">
					<a class="title-content text-bold" href="##"><span>Sản
							phẩm random</span></a>
				</div>
				<div class="row">
					<c:if test="${ listproduct.size() > 0 }">
						<c:set var="countList" value="7" />
						<c:forEach var="item" items="${listproduct}" begin="0"
							end="${ countList }" varStatus="indexs">
							<div class="col-6 col-md-3 mt-3 ">
								<div class="card pt-3" style="width: 100%;">
									<a
										href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
										title="${item.productname}"> <img class="card-img-top"
										src="${pageContext.request.contextPath}/images/${item.productimg}"
										alt="" height="175"">
									</a>
									<div class="card-body text-center">
										<h6 class="card-title" title="${item.productname}">
											<a class="product-item-name"
												href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>"
												title="${item.productname}">${item.productname} </a>
										</h6>
										<p>
											<span class="price"><fmt:formatNumber
													value="${item.productpricesale}" type="number" />đ</span><span
												class="oldprice"><fmt:formatNumber
													value="${item.productprice}" type="number" />đ</span>
										</p>
										<div class="group-action">
											<input type="hidden" class="hidden"
												value="${item.product_id }" /> <a class="btn-buy"
												title="Chi tiết sản phẩm"
												href="<c:url value='/chi-tiet-san-pham/${item.product_slug}'/>">
												<i class="fas fa-search"></i>
											</a> <a class="btn-detail" title="Thêm vào giỏ hàng"
												data-id="${item.product_id}"
												href="<c:url value="/AddCart/${item.product_id}"/>"><i
												class="fas fa-cart-plus"></i></a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	<section class="posts clearfix">
		<div class="container mt-3">
			<div class="block-home-content">
				<div class="title">
					<a class="title-product text-bold" href="##"><span>24h
							công nghệ</span></a>
				</div>
				<div class="row mt-3">
					<c:forEach var="item" items="${listpost}" begin="0" end="0">
						<div class="col-12 col-md-4 mb-3">
							<a href="<c:url value='/bai-viet/${item.post_slug}'/>"> <img
								src="${pageContext.request.contextPath}/images/${item.post_img}"
								class="img-fluid" />
								<div class="post-content">
									<h3>${item.post_title}</h3>
									<span>${item.created_at}</span>
								</div>
							</a>
						</div>
					</c:forEach>
					<div class="col-12 col-md-8 	">
						<div class="row ">
							<c:forEach var="item" items="${listpost}" begin="1" end="6">
								<div class="col-12 col-md-6 mb-3 ">
									<a class="row"
										href="<c:url value='/bai-viet/${item.post_slug}'/>">
										<div class="col-4">
											<img
												src="${pageContext.request.contextPath}/images/${item.post_img}"
												class="img-fluid" />
										</div>
										<div class="col-8">
											<div class="post-content">
												<h3>${item.post_title}</h3>
												<span>${item.created_at}</span>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
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
		$(document).ready(function onDocumentReady() {
			var msg = '${msg}';
			console.log(msg);
			if (msg) {
				toastr.success(msg);
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