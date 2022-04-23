<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${page.page_title}</title>
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
						<li class="breadcrumb-item"><span>Trang đơn</span></li>
						<li class="breadcrumb-item active"><strong><span>${page.page_title}</span></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<div class="detail-content-post shadow-sm mb-3">
					<h1 class="title-head text-center ">${page.page_title}</h1>
					<div class="post-author text-center">
						<span><b>${mapUser[page.created_by]}</b></span> <span>${page.created_at}</span>
					</div>
					<div class="content-detail mt-3 ">${page.page_detail}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>