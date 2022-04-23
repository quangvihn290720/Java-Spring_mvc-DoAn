<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thông tin tài khoản</title>
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
					<h1 class="title-head">Đơn hàng của bạn</h1>
					<div class="recent-orders">
						<div class="table-responsive">
							<table class="table-order" id="my-orders-table">
								<thead>
									<tr>
										<th>Đơn hàng</th>
										<th>Ngày</th>
										<th>Địa chỉ</th>
										<th>Giá trị đơn hàng</th>
										<th>Tình trạng</th>
										<th>TT vận chuyển</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${billUserPaginate}">
										<tr>
											<td>${item.id}</td>
											<td>${item.created_at}</td>
											<td>${item.address},${item.province},${item.district},
												${item.city}</td>
											<td><fmt:formatNumber value="${item.total}"
													type="number" />đ</td>
											<td><c:choose>
													<c:when test="${item.status == -1}">
												Đã hủy
											</c:when>
													<c:when test="${item.status == 0}">
												Đặt hàng
											</c:when>
													<c:when test="${item.status == 1}">
												Đã xác nhận
											</c:when>
													<c:when test="${item.status ==2}">
												Đã giao cho ĐVVC
											</c:when>
													<c:when test="${item.status ==3}">
												Đang vận chuyển
											</c:when>
													<c:when test="${item.status ==4}">
												Đã giao hàng
											</c:when>
												</c:choose></td>
											<td><a
												href="<c:url value="/tai-khoan/don-hang/chi-tiet-don-hang/${item.id}"/>">Chi
													tiết</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center mt-3">
								<!--<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
									varStatus="loop">
									<c:if test="${(loop.index)==paginateInfo.currentPage}">
										<li class="page-item active"><a class="page-link"
											href="<c:url value="/tai-khoan/don-hang/${loop.index}"/>">${loop.index}</a></li>
									</c:if>
									<c:if test="${(loop.index) != paginateInfo.currentPage}">
										<li class="page-item "><a class="page-link"
											href="<c:url value="/tai-khoan/don-hang/${loop.index}"/>">${loop.index}</a></li>
									</c:if>
								</c:forEach>-->
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
	function getPageList(totalPages, page, maxLength) {
	    if (maxLength < 5) throw "maxLength must be at least 5";

	    function range(start, end) {
	        return Array.from(Array(end - start + 1), (_, i) => i + start); 
	    }

	    var sideWidth = maxLength < 9 ? 1 : 2;
	    var leftWidth = (maxLength - sideWidth*2 - 3) >> 1;
	    var rightWidth = (maxLength - sideWidth*2 - 2) >> 1;
	    if (totalPages <= maxLength) {
	        // no breaks in list
	        return range(1, totalPages);
	    }
	    if (page <= maxLength - sideWidth - 1 - rightWidth) {
	        // no break on left of page
	        return range(1, maxLength - sideWidth - 1)
	            .concat(0, range(totalPages - sideWidth + 1, totalPages));
	    }
	    if (page >= totalPages - sideWidth - 1 - rightWidth) {
	        // no break on right of page
	        return range(1, sideWidth)
	            .concat(0, range(totalPages - sideWidth - 1 - rightWidth - leftWidth, totalPages));
	    }
	    // Breaks on both sides
	    return range(1, sideWidth)
	        .concat(0, range(page - leftWidth, page + rightWidth),
	                0, range(totalPages - sideWidth + 1, totalPages));
	}

	// Below is an example use of the above function.
	$(function () {
	    // Number of items and limits the number of items per page
	    // Total pages rounded upwards
	    var totalPages = ${paginateInfo.totalPage};
	   	console.log(totalPages);
	    // Number of buttons at the top, not counting prev/next,
	    // but including the dotted buttons.
	    // Must be at least 5:
	    var paginationSize = 5; 
	    var currentPage;

	    function showPage(whichPage) {
	    	if(totalPages == 1) return false;
	        if (whichPage < 1 || whichPage > totalPages) return false;
	        currentPage = whichPage;
		    // Include the prev/next buttons:
		    $(".pagination").append(
		        $("<li>").addClass("page-item").attr({ id: "previous-page" }).append(
		            $("<a>").addClass("page-link").attr({
		                href: "javascript:void(0)"}).text("Prev")
		        ),
		        $("<li>").addClass("page-item").attr({ id: "next-page" }).append(
		            $("<a>").addClass("page-link").attr({
		                href: "javascript:void(0)"}).text("Next")
		        )
		    );
	        // Replace the navigation items (not prev/next):            
	        $(".pagination li").slice(1, -1).remove();
	        getPageList(totalPages, currentPage, paginationSize).forEach( item => {
	            $("<li>").addClass("page-item")
	                     .addClass(item ? "current-page" : "disabled")
	                     .toggleClass("active", item == whichPage).append(
	                $("<a>").addClass("page-link").attr({
	                    href: '<c:url value="/tai-khoan/don-hang/"/>' + item}).text(item || "...")
	            ).insertBefore("#next-page");
	        });
	        // Disable prev/next when at first/last page:
	        $("#previous-page").toggleClass("disabled", currentPage === 1);
	        $("#next-page").toggleClass("disabled", currentPage === totalPages);
	        return true;
	    }


	    // Show the page links
	    showPage(${currentPage != null?currentPage :1});

	    // Use event delegation, as these items are recreated later    
	    $(document).on("click", ".pagination li.current-page:not(.active)", function () {
	        return showPage(+$(this).text());
	    });
	    $("#next-page").on("click", function () {
	    	if(currentPage<totalPages)
	    	{
	    		return window.location = '<c:url value="/tai-khoan/don-hang/"/>' + (currentPage<totalPages?currentPage+1:currentPage);
	    	}
	    	else return;
	    	
	    });

	    $("#previous-page").on("click", function () {
	    	if(currentPage>1)
	    	{
	    		return window.location = '<c:url value="/tai-khoan/don-hang/"/>' + (currentPage>1?currentPage-1:currentPage);
	    	}
	    	else return;
	    });
	});
    </script>
</body>
</html>