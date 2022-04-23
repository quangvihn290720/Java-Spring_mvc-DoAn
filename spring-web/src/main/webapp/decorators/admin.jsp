<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link
	href="<c:url value='/template/web/assets/images/logo/favicon.ico' />"
	rel="shortcut icon" type="image/x-icon" />
<title><dec:title default="Master-Layout" /></title>
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/all.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/all.min.css"/> ">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

<!-- overlayScrollbars -->
<link rel="stylesheet"
	href="<c:url value="/template/admin/css/adminlte.min.css"/> ">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<link rel="stylesheet"
	href="<c:url value='/template/web/assets/css/bootstrap-steps.min.css'/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="<c:url value="/libraries/ckeditor/ckeditor.js"/>"></script>
<script src="<c:url value="/libraries/ckfinder/ckfinder.js"/>"></script>
</head>
<body>
	<%@ include file="/common/admin/headeradmin.jsp"%>
	<dec:body />

	<%@ include file="/common/admin/footeradmin.jsp"%>
	<script src="<c:url value="/template/admin/js/jquery.min.js"/> "></script>
	<!-- Bootstrap 4 -->
	<script
		src="<c:url value="/template/admin/js/bootstrap.bundle.min.js"/> "></script>
	<!-- AdminLTE App -->
	<script src="<c:url value="/template/admin/js/adminlte.min.js"/> "></script>
	<!-- AdminLTE for demo purposes -->
	<script src="<c:url value="/template/admin/js/demo.js"/> "></script>

</body>
</html>