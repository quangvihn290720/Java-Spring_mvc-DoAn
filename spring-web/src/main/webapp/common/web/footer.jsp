<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<section class="clearfix bg-info py-3">
	<div class="container">
		<div class="row">
			<!-- <div class="col-md-3 col-sm-12 flex-row">
				<div class="img" style="float: left;">
					<img class="img-fluid"
						src="<c:url value='/template/web/assets/images/rocket.png'/>"
						alt="">
				</div>
				<div style="height: auto">
					<span
						style="font-size: 13px; font-weight: bold; text-transform: uppercase;">Giao
						hành nhanh chóng</span>
					<p style="font-size: 13px;">Được cộng đồng bình chọn là shop uy
						tín nhất VN</p>
				</div>
			</div>
			<div class="col-md-3 col-sm-12 flex-row">
				<div class="img" style="float: left;">
					<img class="img-fluid"
						src="<c:url value='/template/web/assets/images/baohanh.png'/>"
						alt="">
				</div>
				<div style="height: auto">
					<span
						style="font-size: 13px; font-weight: bold; text-transform: uppercase;">Uy
						tín 5 sao</span>
					<p style="font-size: 13px;">Được cộng đồng bình chọn là shop uy
						tín nhất VN</p>
				</div>
			</div>
			<div class="col-md-3 col-sm-12 flex-row">
				<div class="img" style="float: left;">
					<img class="img-fluid"
						src="<c:url value='/template/web/assets/images/rm5t.png'/>" alt="">
				</div>
				<div style="height: auto">
					<span
						style="font-size: 13px; font-weight: bold; text-transform: uppercase;">Uy
						tín 5 sao</span>
					<p style="font-size: 13px;">Được cộng đồng bình chọn là shop uy
						tín nhất VN</p>
				</div>
			</div>
			<div class="col-md-3 col-sm-12 flex-row">
				<div class="img" style="float: left;">
					<img class="img-fluid"
						src="<c:url value='/template/web/assets/images/support.png'/>"
						alt="">
				</div>
				<div style="height: auto">
					<span
						style="font-size: 13px; font-weight: bold; text-transform: uppercase;">hỗ
						trợ tận tình</span>
					<p style="font-size: 13px;">Đội ngũ hộ trợ tận tình</p>
				</div>
			</div>-->
			<c:forEach var="item" items="${listservice }">
				<div class="col-md-3 col-sm-12 flex-row">
					<div class="img" style="float: left;">
						<img class="img-fluid"
							src="<c:url value='/images/${item.img }' />" alt="">
					</div>
					<div style="height: auto">
						<span
							style="font-size: 13px; font-weight: bold; text-transform: uppercase;">${item.name }</span>
						<p style="font-size: 13px;">${item.metadesc }</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</section>
<c:url var="url" value="/trang-don" />
<section class="clearfix">
	<div class="footer-dark">
		<footer>
			<div class="container">
				<div class="row">
					<c:forEach var="item" items="${listtopic}">
						<div class="col-sm-6 col-md-3 item">
							<h3>${item.topic_name}</h3>
							<ul>
								<c:forEach var="items" items="${pages}" varStatus="index">
									<c:if test="${item.topic_id == items.page_topic}">
										<li><a href="${url}/${items.page_slug}">${items.page_title}</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
					<div class="col-md-6 item text">
						<h3>${configweb.nameweb }</h3>
						<p>${configweb.web_detail }</p>
						<%
							if (session.getAttribute("count") != null) {
						%>
						<p>
							Số người từng ghé:
							<%=session.getAttribute("count")%></p>
						<%
							}
						%>
					</div>
					<div class="col item social">
						<!-- <a href="https://www.facebook.com/ngdquangvinh297"><i
							class="fab fa-facebook-f"></i></a> <a
							href="https://www.facebook.com/ngdquangvinh297"><i
							class="fab fa-twitter"></i></a> <a
							href="https://www.facebook.com/ngdquangvinh297"><i
							class="fab fa-youtube"></i></a> <a
							href="https://www.facebook.com/ngdquangvinh297"><i
							class="fab fa-instagram"></i></a> -->
						<c:forEach var="items" items="${socialnetworks}" varStatus="index">
							<a href="${items.address }" target="_blank">${items.icon}</a>
						</c:forEach>
					</div>
				</div>
				<p class="copyright">© Bản quyền thuộc về Thành & Vinh</p>
			</div>
		</footer>
	</div>
</section>
