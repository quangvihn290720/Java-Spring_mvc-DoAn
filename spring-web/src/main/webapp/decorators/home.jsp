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
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/ft.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/bootstrap.min.css'/>">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
	integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://jqueryvalidation.org/files/demo/site-demos.css">
<link rel="stylesheet"
	href=" https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src=" https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js ">
	
</script>
</head>
<body>
	<!-- Navigation -->
	<%@ include file="/common/web/headerhome.jsp"%>

	<dec:body />

	<!-- Footer -->
	<%@ include file="/common/web/footer.jsp"%>

	<!-- Bootstrap core JavaScript -->

	<script src="<c:url value='/template/web/assets/js/bootstrap.min.js'/>"></script>
	<script src=" https://uhchat.net/code.php?f=d7eac7 ">
		
	</script>


</body>
</html>