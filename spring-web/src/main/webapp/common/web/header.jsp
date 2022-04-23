<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v10.0"
	nonce="ZrqLjonX"></script>
<div class="backdrop__body-backdrop___1rvky"></div>
<div class="mobile-main-menu">
	<div class="la-scroll-fix-infor-user">
		<button class="close-menu" aria-label="Đóng menu" title="Đóng menu">
			<svg height="15px" viewBox="0 0 329.26933 329" width="15px"
				xmlns="http://www.w3.org/2000/svg">
				<path
					d="m194.800781 164.769531 128.210938-128.214843c8.34375-8.339844 8.34375-21.824219 0-30.164063-8.339844-8.339844-21.824219-8.339844-30.164063 0l-128.214844 128.214844-128.210937-128.214844c-8.34375-8.339844-21.824219-8.339844-30.164063 0-8.34375 8.339844-8.34375 21.824219 0 30.164063l128.210938 128.214843-128.210938 128.214844c-8.34375 8.339844-8.34375 21.824219 0 30.164063 4.15625 4.160156 9.621094 6.25 15.082032 6.25 5.460937 0 10.921875-2.089844 15.082031-6.25l128.210937-128.214844 128.214844 128.214844c4.160156 4.160156 9.621094 6.25 15.082032 6.25 5.460937 0 10.921874-2.089844 15.082031-6.25 8.34375-8.339844 8.34375-21.824219 0-30.164063zm0 0" /></svg>
		</button>
		<div class="user-info">
			<div class="user-avatar">
				<div class="no-avt">
					<svg height="25px" viewBox="0 0 329.26933 329" width="25px"
						xmlns="http://www.w3.org/2000/svg">
						<path
							d="m194.800781 164.769531 128.210938-128.214843c8.34375-8.339844 8.34375-21.824219 0-30.164063-8.339844-8.339844-21.824219-8.339844-30.164063 0l-128.214844 128.214844-128.210937-128.214844c-8.34375-8.339844-21.824219-8.339844-30.164063 0-8.34375 8.339844-8.34375 21.824219 0 30.164063l128.210938 128.214843-128.210938 128.214844c-8.34375 8.339844-8.34375 21.824219 0 30.164063 4.15625 4.160156 9.621094 6.25 15.082032 6.25 5.460937 0 10.921875-2.089844 15.082031-6.25l128.210937-128.214844 128.214844 128.214844c4.160156 4.160156 9.621094 6.25 15.082032 6.25 5.460937 0 10.921874-2.089844 15.082031-6.25 8.34375-8.339844 8.34375-21.824219 0-30.164063zm0 0" /></svg>
				</div>
			</div>
			<div class="user-name">
				<c:if test="${empty LoginInfo}">
					<p>
						<a href="<c:url value="/dang-nhap"/>" title="Đăng nhập">Đăng
							nhập</a>
					</p>
					<p>
						<a href="<c:url value="/dang-ky"/>" title="Đăng ký">Đăng ký</a>
					</p>
				</c:if>
				<c:if test="${not empty LoginInfo}">
					<p>
						Xin chào <a href="<c:url value="/tai-khoan"/>" title="">${LoginInfo.user_fullname}</a>
					</p>
					<p>
						<a href="<c:url value="/dang-xuat"/>" title="Thoát">Thoát</a>
					</p>
				</c:if>
			</div>
		</div>
		<!-- 2 cap -->
		<ul class="la-nav-list-items">
			<c:forEach var="item" items="${listcate}" varStatus="index">
				<c:choose>
					<c:when test="${empty mapcatproducts[item.id]}">
						<li class='ng-scope'><a
							href="<c:url value="/${item.slug}?page=1"/>">${item.name}</a></li>
					</c:when>
					<c:otherwise>
						<li class='ng-scope ng-has-child1'><a
							href="<c:url value="/${item.slug}?page=1"/>">${item.name}<i
								class="fas fa-plus fa-plus1"></i></a> <c:forEach var="itemprod"
								items="${listproductmenu}">
								<c:if test="${itemprod.product_catid == item.id}">
									<c:forEach var="itemmanu" items="${listmanu}">
										<c:if
											test="${itemmanu.manufacturer_id == itemprod.manufacturer_id}">
											<ul class="ul-has-child1" style="display: none;">
												<li class="ng-scope"><a
													href="<c:url value="/${item.slug}-${itemmanu.manufacturer_slug}?page=1"/>"
													title="${itemmanu.manufacturer_name}">${itemmanu.manufacturer_name}</a></li>
											</ul>
										</c:if>
									</c:forEach>
								</c:if>
							</c:forEach></li>
					</c:otherwise>
				</c:choose>


			</c:forEach>
			<li class="ng-scope"><a href="#" title="Đồng hồ">Đồng hồ</a></li>
			<li class="mb-split">Menu</li>
			<c:forEach var="item" items="${menu}">
				<li class="ng-scope "><a
					href="<c:url value="/${item.menu_slug}?page=1"/>"
					title="${item.menu_name}">${item.menu_name}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<section class="clearfix">
	<div class="topbar">
		<img src="${pageContext.request.contextPath }/images/${topbar.img }"
			alt="${topbar.name }" style="width: 100%;">
	</div>
</section>
<section class="clearfix d-none d-lg-block">
	<div class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-4 col-lg-3" id="logoo">
					<div class="lolo">
						<a class="logo-link" href="<c:url value='/'/>"> <img
							class="img-logo"
							src="${pageContext.request.contextPath }/images/${configweb.logo }"
							alt="">
						</a>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-8 col-lg-9">
					<div class="header-info hidden-xs">
						<div class="row">
							<div class="col-sm-12 col-md-12 col-lg-12">
								<div class="account-area">
									<ul class="account-info">
										<c:if test="${empty LoginInfo}">
											<li><a href="<c:url value="/dang-nhap"/>">Đăng nhập</a></li>
											<li><a href="<c:url value="/dang-ky"/>">Đăng kí</a></li>
										</c:if>

										<c:if test="${not empty LoginInfo}">
											<li>Xin chào <a href="<c:url value="/tai-khoan"/>">
													${LoginInfo.user_fullname}</a></li>
											<li><a href="<c:url value="/dang-xuat"/>">Thoát</a></li>
										</c:if>
									</ul>
									<div class="top-cart-cotain">
										<div class="mini-cart text-xs-center">
											<div class="headding-cart">
												<a href="<c:url value="/gio-hang"/>"> <img
													src="<c:url value='/template/web/assets/images/icon_cart.png'/> "
													alt="asds" /> <span class="cart-count" id="cart-total"><c:choose>
															<c:when test="${not empty Cart}">${TotalQuantyCart}
								</c:when>
															<c:otherwise>0
								</c:otherwise>
														</c:choose></span> <span class="cart-text">Giỏ hàng</span>
												</a>
											</div>
										</div>

									</div>
								</div>

							</div>
						</div>
						<div class="row">
							<div class="d-md-none d-lg-block col-lg-12">
								<div class="header-menu">
									<nav>
										<ul id="nav" class="nav">
											<c:forEach var="item" items="${menu}" varStatus="index">
												<li class="nav-item"><a class="nav-link"
													href="<c:url value="/${item.menu_slug}?page=1"/>">${item.menu_name}
														<c:if test="${not empty listcmenu[item.menu_id]}">
															<i class="fas fa-caret-down"></i>

														</c:if>
												</a> <c:if test="${not empty listcmenu[item.menu_id]}">
														<ul class="dropdown-menu">
															<c:forEach var="childitem"
																items="${listcmenu[item.menu_id]}">
																<li class="nav-item"><a
																	href="<c:url value="/${item.menu_slug}/${childitem.menu_slug}?page=1"/>">${childitem.menu_name}</a></li>
															</c:forEach>
														</ul>
													</c:if>
											</c:forEach>
										</ul>
									</nav>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<section class="header-other">
	<div class="header-top container d-sm-block d-lg-none">
		<div class="row align-items-center header-padding">
			<div class="col-lg-2 col-md-2 header-logo header-flex-item">
				<button class="d-sm-inline-block d-lg-none menu-icon"
					aria-label="Menu" id="trigger-mobile2" title="Menu">
					<svg xmlns="http://www.w3.org/2000/svg"
						xmlns:xlink="http://www.w3.org/1999/xlink"
						xmlns:svgjs="http://svgjs.com/svgjs" version="1.1" width="20px"
						height="20px" x="0" y="0" viewBox="0 0 384 384"
						style="enable-background: new 0 0 512 512" xml:space="preserve"
						class="">
						<g>
						<path xmlns="http://www.w3.org/2000/svg"
							d="m368 154.667969h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0"
							fill="#fdfcfc" data-original="#000000" style="" class="" />
						<path xmlns="http://www.w3.org/2000/svg"
							d="m368 32h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0"
							fill="#fdfcfc" data-original="#000000" style="" class="" />
						<path xmlns="http://www.w3.org/2000/svg"
							d="m368 277.332031h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0"
							fill="#fdfcfc" data-original="#000000" style="" class="" /></g>
								</svg>
				</button>
				<a href="<c:url value='/'/>" class="logo-wraper" title="Ten web">
					<img
					src="${pageContext.request.contextPath }/images/${configweb.logomobile }"
					alt="">
				</a>
			</div>
			<div class="col-lg-2 col-md-2 header-flex-item-mobile">
				<div class="mb-hotline">
					<a href="tel:${configweb.hotline }" title="Hotline"><span>Hotline</span>${configweb.hotline }</a>
					<a href="<c:url value="/gio-hang"/>" title="Giỏ hàng" class="mb-header-cart"><i
						class="fas fa-shopping-cart"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="header-bottom header-other-page">
		<div class="container">
			<div class="row">
				<div class="col-xl-3 col-lg-3 cate-header d-lg-block d-none">
					<div class="titles">
						<button class="menu-icon" aria-label="Menu" title="Menu">
							<svg height="17px" viewBox="0 -53 384 384" width="20px"
								xmlns="http://www.w3.org/2000/svg">
								<path
									d="m368 154.667969h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0" />
								<path
									d="m368 32h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0" />
								<path
									d="m368 277.332031h-352c-8.832031 0-16-7.167969-16-16s7.167969-16 16-16h352c8.832031 0 16 7.167969 16 16s-7.167969 16-16 16zm0 0" /></svg>
						</button>
						Danh mục
					</div>
					<div class="width-cate">
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
										<!-- 	<li class="main-cate-has-child clearfix"><a href="#"
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
														.siblings(
																'li.toggleable')
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
				</div>
				<div class="col-xl-7 col-lg-7  search-header search-desktop">
					<div class="top-search fw">
						<form action='<c:url value="/search"/>' method="GET"
							class="search-form" style="width: 100%">
							<div class="input-group">
								<input type="text" class="form-control rounded-0" maxlength="70"
									name="q" placeholder="Nhập từ khóa tìm kiếm..." required>
								<input type="hidden" name="currentPage" value="1">
								<button class="btn-search btn rounded-0" type="submit">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
					</div>
				</div>
				<div class="col-xl-2 col-lg-2 hotline-header d-lg-flex d-none">
					<div class="main-hotline">
						<a href="tel:${configweb.hotline }"> <span>Hotline:</span> ${configweb.hotline }
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.ng-has-child1 a .fa-plus1').on(
								'click',
								function(e) {

									e.preventDefault();
									var $this = $(this);
									$this.parents('.ng-has-child1').find(
											'.ul-has-child1').stop()
											.slideToggle();
									$(this).toggleClass('active');
									return false;
								});
						$(
								'.ng-has-child1 .ul-has-child1 .ng-has-child2 a .fa-plus2')
								.on(
										'click',
										function(e) {
											e.preventDefault();
											var $this = $(this);
											$this
													.parents(
															'.ng-has-child1 .ul-has-child1 .ng-has-child2')
													.find('.ul-has-child2')
													.stop().slideToggle();
											$(this).toggleClass('active');
											return false;
										});
					});
</script>
<script type="text/javascript">
	$("#trigger-mobile2").click(function() {
		// alert("The paragraph was clicked.");
		$('.mobile-main-menu').toggleClass('active');
		$('.backdrop__body-backdrop___1rvky').toggleClass('active');
		// // $('#btnDiv').addClass('rotated');
	});
	$(".close-menu").click(function() {
		$('.mobile-main-menu').removeClass('active');
		$('.backdrop__body-backdrop___1rvky').removeClass('active');
		// // $('#btnDiv').addClass('rotated');
	});
	$(".backdrop__body-backdrop___1rvky").click(function() {
		$('.mobile-main-menu').removeClass('active');
		$('.backdrop__body-backdrop___1rvky').removeClass('active');
		// // $('#btnDiv').addClass('rotated');
	});
</script>