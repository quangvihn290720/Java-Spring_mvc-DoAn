<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bài viết</title>
</head>
<body>
	<div class="content-wrapper pt-3">
		<!-- Content Header (Page header) -->
		<c:url var="add" value="/quan-tri/bai-viet/add" />
		<c:url var="get" value="/quan-tri/bai-viet/get" />
		<c:url var="edit" value="/quan-tri/bai-viet/edit" />
		<c:url var="onoff" value="/quan-tri/bai-viet/status" />
		<c:url var="deltrash" value="/quan-tri/bai-viet/trash" />
		<c:url var="trash" value="/quan-tri/bai-viet/thung-rac" />
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
						<strong class="text-uppercase text-danger">Danh sách bài
							viết<c:out value="${mapTopic[3]}" />
						</strong>
					</h3>
					<div class="card-tools">
						<a class="btn btn-sm btn-success" href="${add}"><i
							class="fas fa-plus"></i> Thêm</a> <a class="btn btn-sm btn-danger"
							href="${trash }"><i class="fas fa-trash"></i> Thùng rác</a>
					</div>
				</div>
				<div class="card-body">
					<table class="table table-bordered border-hover ">
						<thead>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Tên bài viết</th>
								<th>Chủ đề bài viết</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${ postPaginate }">
								<tr>
									<td>${item.post_id}</td>
									<td><img style="width: 100%;"
										src="${pageContext.request.contextPath}/images/${item.post_img}"></td>
									<td>${item.post_title}</td>
									<td>${mapTopic[item.post_topicid]}</td>
									<td><c:choose>
											<c:when test="${item.post_status==1}">
												<a href="${onoff }/${item.post_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-success"><i
													class="fas fa-toggle-on"></i></a>
											</c:when>
											<c:when test="${item.post_status==2}">
												<a href="${onoff }/${item.post_id }"
													onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
													class="btn btn-sm btn-danger"><i
													class="fas fa-toggle-off"></i></a>
											</c:when>
										</c:choose> <a href="${edit }/${item.post_id }"
										class="btn btn-sm btn-info"><i class="far fa-edit"></i></a> <a
										href="${deltrash }/${item.post_id }"
										onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
										class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<th style="width: 40px;" class="text-center">ID</th>
								<th style="width: 200px;">Hình ảnh</th>
								<th>Tên bài viết</th>
								<th>Chủ đề bài viết</th>
								<th style="width: 10rem;">Chức năng</th>
							</tr>
						</tfoot>
					</table>
				</div>
				<!--<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:forEach var="item" begin="1" end="${paginateInfo.totalPage}"
							varStatus="loop">
							<c:if test="${(loop.index)==paginateInfo.currentPage}">
								<li class="page-item active"><a class="page-link"
									href="<c:url value="/quan-tri/bai-viet/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
							<c:if test="${(loop.index) != paginateInfo.currentPage}">
								<li class="page-item "><a class="page-link"
									href="<c:url value="/quan-tri/bai-viet/${loop.index}"/>">${loop.index}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</nav>-->
				<nav aria-label="Page navigation example">
					<div class="pagination justify-content-center"></div>
				</nav>
			</div>
			<!-- /.card -->
		</section>
		<!-- /.content -->
	</div>
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
	        if (whichPage < 1 || whichPage > totalPages) return false;
	        currentPage = whichPage;
	        // Replace the navigation items (not prev/next):            
	        $(".pagination li").slice(1, -1).remove();
	        getPageList(totalPages, currentPage, paginationSize).forEach( item => {
	            $("<li>").addClass("page-item")
	                     .addClass(item ? "current-page" : "disabled")
	                     .toggleClass("active", item == whichPage).append(
	                $("<a>").addClass("page-link").attr({
	                    href: '<c:url value="/quan-tri/bai-viet/"/>' + item}).text(item || "...")
	            ).insertBefore("#next-page");
	        });
	        // Disable prev/next when at first/last page:
	        $("#previous-page").toggleClass("disabled", currentPage === 1);
	        $("#next-page").toggleClass("disabled", currentPage === totalPages);
	        return true;
	    }

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
	    // Show the page links
	    showPage(${currentPage != null?currentPage :1});

	    // Use event delegation, as these items are recreated later    
	    $(document).on("click", ".pagination li.current-page:not(.active)", function () {
	        return showPage(+$(this).text());
	    });
	    $("#next-page").on("click", function () {
	    	if(currentPage<totalPages)
	    	{
	    		return window.location = '<c:url value="/quan-tri/bai-viet/"/>' + (currentPage<totalPages?currentPage+1:currentPage);
	    	}
	    	else return;
	    	
	    });

	    $("#previous-page").on("click", function () {
	    	if(currentPage>1)
	    	{
	    		return window.location = '<c:url value="/quan-tri/bai-viet/"/>' + (currentPage>1?currentPage-1:currentPage);
	    	}
	    	else return;
	    });
	});
    </script>
</body>

</html>