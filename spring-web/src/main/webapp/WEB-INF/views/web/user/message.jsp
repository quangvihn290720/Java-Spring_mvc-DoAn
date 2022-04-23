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
				<c:if test="${not empty message}">
					<div
						style="text-align: center; color: red; font-weight: 900; margin-top: 200px"
						class="alert alert-danger">${message}</div>
				</c:if>
				<div style="display: flex; justify-content: center;">
					<a href="<c:url value="/dang-nhap"/>"
						style="text-align: center; color: red; font-weight: 900;"
						class="button">Đăng nhập</a>
				</div>

			</div>
			<div class="overlay-container">
				<div class="overlay">
					<div class="overlay-panel overlay-left"></div>
					<div class="overlay-panel overlay-right">
						<h1>Chào bạn!</h1>
						<p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với
							chúng tôi</p>
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
</body>
</html>

