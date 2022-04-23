<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="${pageContext.request.contextPath }/images/${configweb.icon }"
	rel="shortcut icon" type="image/x-icon" />
<meta charset="UTF-8">
<title><dec:title default="Master-Layout" /></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<!-- <script src="https://code.jquery.com/jquery-migrate-3.3.2.js"></script> -->
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/ft.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/bootstrap-steps.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/assets/owlcarousel/assets/owl.carousel.min.css'/> " />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/assets/owlcarousel/assets/owl.theme.default.min.css'/>" />
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/assets/slick/slick.css'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/assets/slick/slick-theme.css'/> " />
<link rel="stylesheet"
	href=" https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src=" https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js "></script>
<script
	src="<c:url value='/template/web/assets/owlcarousel/owl.carousel.min.js'/>"></script>
<dec:head></dec:head>
</head>
<body>

	<%@ include file="/common/web/header.jsp"%>

	<dec:body />

	<!-- Footer -->
	<%@ include file="/common/web/footer.jsp"%>

	<script src="<c:url value='/template/web/assets/js/bootstrap.min.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/template/web/assets/slick/slick.min.js'/>"></script>
	<dec:getProperty property="page.script"></dec:getProperty>
</body>
</html>