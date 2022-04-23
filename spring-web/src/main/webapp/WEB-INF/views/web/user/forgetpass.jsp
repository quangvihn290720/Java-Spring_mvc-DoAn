<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Khôi phục mật khẩu</title>
</head>
<body>
	<section class="clearfix">
		<h2>Khôi phục mật khẩu</h2>
		<div class="container" id="container">
			<div class="form-container sign-up-container"></div>
			<div class="form-container sign-in-container">
				<form action="<c:url value="/quen-mat-khau"/>" method="POST">
					<p style="margin-bottom: 40px;">Vui lòng cung cấp email đăng
						nhập để khôi phục mật khẩu của bạn.</p>
					<input type='text' name='user_email' placeholder="gg@gmail.com"
						required="required" />
					<div style="margin-bottom: 80px;" class="g-recaptcha"
						data-sitekey="6Ld7Je4aAAAAACtAk0otGI9a9MPvjdupVWZZu2hi"></div>
					<button name="submit" type="submit">Xác nhận</button>
				</form>
			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-left"></div>
					<div class="overlay-panel overlay-right">
						<h1>Quên mật khẩu?</h1>
						<p>Vui lòng nhập tài khoản gmail bạn muốn lấy lại mật khẩu.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
		function validate(evt) {
			var theEvent = evt || window.event;

			// Handle paste
			if (theEvent.type === 'paste') {
				key = event.clipboardData.getData('text/plain');
			} else {
				// Handle key press
				var key = theEvent.keyCode || theEvent.which;
				key = String.fromCharCode(key);
			}
			var regex = /[0-9]|\./;
			if (!regex.test(key)) {
				theEvent.returnValue = false;
				if (theEvent.preventDefault)
					theEvent.preventDefault();
			}
		}
	</script>
	<script>
		toastr.options = {
			"closeButton" : false,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-full-width",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "0",
			"hideDuration" : "1000",
			"timeOut" : "5000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		};
		$(document).ready(function onDocumentReady() {
			var msg = '${msg}';
			console.log(msg);
			if (msg) {
				toastr.warning(msg);
			}
		});
	</script>
</body>
</html>
