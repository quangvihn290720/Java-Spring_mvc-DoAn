<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>
	<section class="clearfix bread-crumb">
		<span class="bread-cumb-border"></span>
		<div class="container">
			<div class="row">
				<div class="col-12">
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a href="<c:url value="/trang-chu"/>"><span>Trang
									chủ</span></a></li>
						<li class="breadcrumb-item active"><strong><span>Liên
									hệ</span></a></strong></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<section class="contact-section">
		<div class="container">
			<div class="row">
				<div class="container">
					<c:if test="${not empty msgSs}">
						<div class="alert alert-success mt-3">${ msgSs}</div>
					</c:if>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="container mt-1">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1745.6040240629839!2d106.7748480652859!3d10.830554180529228!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752701a34a5d5f%3A0x30056b2fdf668565!2zVHLGsOG7nW5nIENhbyDEkOG6s25nIEPDtG5nIFRoxrDGoW5nIFRQLkhDTQ!5e0!3m2!1svi!2s!4v1619953996719!5m2!1svi!2s"
							width="100%" height="450" style="border: 0;" allowfullscreen=""
							loading="lazy"></iframe>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 your-message mb-2">
					<c:url var="send" value="/lien-he/send" />
					<form:form modelAttribute="contact" id="contact" action="${send}"
						method="post">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Họ và tên</label>
								<form:input type="text" class="form-control" id="inputEmail4"
									path="name" required="required" placeholder="Họ và tên" />
							</div>
							<div class="form-group col-md-6">
								<label>Email</label>
								<form:input type="email" class="form-control"
									id="inputPassword4" path="email" required="required"  placeholder="Email"/>
							</div>
						</div>
						<div class="form-group">
							<label>Đia chỉ</label>
							<form:input type="text" class="form-control" id="inputAddress"
								path="address" required="required"  placeholder="Địa chỉ"/>
						</div>
						<div class="form-group">
							<label>Điện thoại</label>
							<form:input type="text" class="form-control" id="inputPhone"
								path="phone" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"
								title="Số điện thoại sai định dạng" required="required"  placeholder="Số điện thoại"/>
						</div>
						<div class="form-group">
							<label>Tiêu đề</label>
							<form:input type="text" class="form-control" path="subject" required="required"  placeholder="Tiêu đề"/>
						</div>
						<div class="form-group">
							<label>Nội dung</label>
							<form:textarea type="text" class="form-control" rows="3"
								path="content" required="required"  placeholder="Nội dung góp ý"/>
						</div>
						<button type="submit" class="btn btn-primary"
							style="float: right;">Gửi tin nhắn</button>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script>
		function setInputFilter(textbox, inputFilter) {
			[ "input", "keydown", "keyup", "mousedown", "mouseup", "select",
					"contextmenu", "drop" ].forEach(function(event) {
				textbox.addEventListener(event, function() {
					if (inputFilter(this.value)) {
						this.oldValue = this.value;
						this.oldSelectionStart = this.selectionStart;
						this.oldSelectionEnd = this.selectionEnd;
					} else if (this.hasOwnProperty("oldValue")) {
						this.value = this.oldValue;
						this.setSelectionRange(this.oldSelectionStart,
								this.oldSelectionEnd);
					} else {
						this.value = "";
					}
				});
			});
		}
		setInputFilter(document.getElementById("inputPhone"), function(value) {
			return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
		});
	</script>
</body>
</html>