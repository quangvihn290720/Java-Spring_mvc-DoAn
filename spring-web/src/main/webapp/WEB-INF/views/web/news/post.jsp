<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${itempost.post_title}</title>
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
						<li class="breadcrumb-item"><a
							href="<c:url value="/bai-viet?page=1"/>"><span>Tin tức</span></a></li>
						<li class="breadcrumb-item active"><strong><span>${itempost.post_title}</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="detail-content-post shadow-sm mb-3">
					<h1 class="title-head text-center ">${itempost.post_title}</h1>
					<div class="post-author text-center">
						<span><b>${mapUser[itempost.created_by]}</b></span> <span>${itempost.created_at}</span>
					</div>
					<div class="content-detail mt-3 ">${itempost.post_detail}</div>
				</div>
				<!-- <div class="tags-post shadow-sm">
					<span>Tags: </span> <a href="#" class="rounded">iphone12</a> <a
						href="#" class="rounded">test</a>
				</div> -->
				<div class="post-related shadow-sm mt-3">
					<div class="title">Bài viết liên quan</div>
					<div class="row">
						<c:forEach var="item" items="${listpostrelated}">
							<div class="col-lg-4 col-md-6 col-sm-6">
								<div class="news-rl-item">
									<a href="${item.post_slug}" title="${item.post_slug}"
										class="news-rl-item_img"> <img
										src="${pageContext.request.contextPath}/images/${item.post_img}"
										class="img-fluid">
									</a>
									<div class="news-rl-item_info">
										<a href="${item.post_slug}" class="news-rl-item_info_title">${item.post_title}</a>
										<p class="news-rl-item_info_time">${item.created_at}</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>