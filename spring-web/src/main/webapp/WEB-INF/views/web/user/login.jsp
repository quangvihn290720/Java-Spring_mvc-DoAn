<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập & đăng ký</title>
</head>
<body>
	<%
		String username = "", password = "";
	boolean remember = false;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie ck : cookies) {
			if (ck.getName().equalsIgnoreCase("username")) {
		username = ck.getValue();
			} else if (ck.getName().equalsIgnoreCase("password")) {
		password = ck.getValue();
			}
		}
		if (!username.isEmpty() && !password.isEmpty())
			remember = true;
	}
	%>
	<section class="clearfix">
		<h2>Đăng nhập / Đăng ký</h2>
		<c:if test="${not empty msgfail}">
			<div style="text-align: center; color: red; font-weight: 900;"
				class="alert alert-danger">${msgfail}</div>
		</c:if>
		<c:if test="${not empty msgsuccess}">
			<div style="text-align: center; color: green; font-weight: 900;"
				class="alert alert-success">${msgsuccess}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div style="text-align: center; color: red; font-weight: 900;"
				class="alert alert-danger">${msg}</div>
		</c:if>
		<c:choose>
			<c:when test="${regis == 1}">
				<div class="container right-panel-active" id="container">
			</c:when>
			<c:otherwise>
				<div class="container" id="container">
			</c:otherwise>
		</c:choose>
		<div class="form-container sign-up-container ">
			<form:form modelAttribute="user" action="dang-ky" method="POST">
				<h1>Đăng ký</h1>
				<form:input type="text" placeholder="Username" path="username"
					required="required" />
				<form:input type="password" placeholder="Password" path="password"
					required="required" />
				<form:input type="text" placeholder="FullName" path="user_fullname"
					required="required" />
				<form:input type="email" placeholder="Email" path="user_email"
					required="required" />
				<form:input type="text" placeholder="Phone" path="user_phone"
					required="required" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"
					title="Số điện thoại sai định dạng" />
				<button type="submit">Đăng ký</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form:form>
		</div>
		<div class="form-container sign-in-container">
			<form name='loginForm' action="<c:url value='/dang-nhap' />"
				method="POST">
				<h1>Đăng nhập</h1>
				<input type='text' name='username' value="<%=username%>"
					placeholder="Username" required="required" /> <input
					type='password' name='password' value="<%=password%>"
					placeholder="Password" required="required" />
				<p>
					<input type="checkbox" name="remember"
						<%=remember == true ? "checked='checked'" : ""%>
						style="width: auto;" /><label for="remember">Remember me</label>
				</p>
				<p>
					<a href="<c:url value="/quen-mat-khau"/>">Quên mật khẩu?</a>
				</p>
				<button name="submit" type="submit">Đăng nhập</button>
			</form>
		</div>
		<div class="overlay-container">
			<div class="overlay">
				<div class="overlay-panel overlay-left">
					<h1>Chào mừng trở lại!</h1>
					<p>Để giữ kết nối với chúng tôi, vui lòng đăng nhập bằng thông
						tin cá nhân của bạn thông tin</p>
					<button class="ghost" id="signIn">Đăng nhập</button>
				</div>
				<div class="overlay-panel overlay-right">
					<h1>Chào bạn!</h1>
					<p>Nhập thông tin cá nhân của bạn và bắt đầu hành trình với
						chúng tôi</p>
					<button class="ghost" id="signUp">Đăng ký</button>
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
		  if( !regex.test(key) ) {
		    theEvent.returnValue = false;
		    if(theEvent.preventDefault) theEvent.preventDefault();
		  }
		}
	</script>
	<script>
        const signUpButton = document.getElementById('signUp');
        const signInButton = document.getElementById('signIn');
        const container = document.getElementById('container');

        signUpButton.addEventListener('click', () => {
            container.classList.add('right-panel-active');
        });

        signInButton.addEventListener('click', () => {
            container.classList.remove('right-panel-active');
        });
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
			var message = '${message}';
			console.log(message);
			if (message) {
				toastr.error(message);
			}
		});
	</script>
</body>
</html>
