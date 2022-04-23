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
	<section class="clearfix">
		<section class="clearfix bread-crumb">
			<span class="bread-cumb-border"></span>
			<div class="container">
				<div class="row">
					<div class="col-12">
						<ul class="breadcrumb">
							<li class="breadcrumb-item"><a
								href="<c:url value="/trang-chu"/>"><span>Trang chủ</span></a></li>
							<li class="breadcrumb-item active"><strong><span>Tin
										tức</span></strong></li>
						</ul>
					</div>
				</div>
			</div>
		</section>
		<div class="container">
			<div class="news">
				<div class="news-header">
					<h1 class="news-h1">${mapTopic[topicEntity.topic_id]}</h1>
					<ul class="news-menu margin-bottom-10">
						<c:forEach var="item" items="${listtopicshow}">
							<li class="news-menu__item"><a
								href="<c:url value="/bai-viet/${item.topic_slug}"/>"
								title="${item.topic_name}">${item.topic_name}</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="row">
					<div class="col-md-12 col-lg-9">
						<div class="news-top">
							<div class="news-top_left">
								<c:forEach var="item" items="${listpostbytopic}" begin="0"
									end="0" varStatus="index">
									<div class="news-top_item">
										<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
											title="${item.post_title}" class="news-top-item_img"> <img
											src="${pageContext.request.contextPath}/images/${item.post_img}"
											alt="${item.post_title}" class="img-fluid">
										</a>
										<h3 class="news-top_item_title">
											<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
												title="${item.post_title}"> ${item.post_title}</a>
										</h3>
										<p class="news-top_item_time">${item.created_at}</p>
									</div>
								</c:forEach>
							</div>
							<div class="news-top_right">
								<c:forEach var="item" items="${listpostbytopic}" begin="1"
									end="4" varStatus="index">
									<div class="news-item">
										<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
											title="${item.post_title}" class="news-item_img"> <img
											src="${pageContext.request.contextPath}/images/${item.post_img}"
											alt="${item.post_title}" class="img-fluid">
										</a>
										<div class="news-item_info">
											<h3>
												<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
													title="${item.post_title}">${item.post_title}</a>
											</h3>
											<p class="news-item-time">${item.created_at}</p>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="list-news mb-3">
							<c:forEach var="item" items="${listpostbytopic}" begin="5"
								end="10" varStatus="index">
								<div class="news-item">
									<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
										title="${item.post_title}" class="news-item_img"> <img
										src="${pageContext.request.contextPath}/images/${item.post_img}"
										alt="${item.post_title}" class="img-fluid">
									</a>
									<div class="news-item_info">
										<h3>
											<a href="<c:url value='/bai-viet/${item.post_slug}'/>"
												title="${item.post_title}">${item.post_title}</a>
										</h3>
										<div class="news-item-text">${item.post_metadesc}</div>
										<p class="news-item-time">${item.created_at}</p>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>