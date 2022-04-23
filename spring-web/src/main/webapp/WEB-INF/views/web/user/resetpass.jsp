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
				<form action="<c:url value="/doi-mat-khau"/>" method="POST">
					<h1>Khôi phục mật khẩu</h1>
					<input type="hidden" name="token" value="${token}" /> 
					<input
						type="password" name="password" id="password" class="form-control"
						placeholder="Enter your new password" required autofocus /> 
					<input
						type="password" class="form-control"
						placeholder="Confirm your new password" required
						oninput="checkPasswordMatch(this);" />
					<button name="submit" type="submit">Xác nhận</button>
				</form>
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
		function checkPasswordMatch(fieldConfirmPassword) {
			if (fieldConfirmPassword.value != $("#password").val()) {
				fieldConfirmPassword
						.setCustomValidity("Passwords do not match!");
			} else {
				fieldConfirmPassword.setCustomValidity("");
			}
		}
	</script>
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

