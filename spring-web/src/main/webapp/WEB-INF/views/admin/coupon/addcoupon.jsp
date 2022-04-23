<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm mã khuyến mãi</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/web/ma-khuyen-mai/save" />
	<c:url var="list" value="/quan-tri/web/ma-khuyen-mai" />
	<form:form modelAttribute="coupon" action="${save}" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm mã
								khuyến mãi mới</strong>
						</h3>
						<div class="card-tools">
							<button type="submit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list }"> Quay về
								danh sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label>Mã khuyến mãi</label> <span style="float: right;"><a
										href="" id="randomString" onclick="randomString()">Tạo mã
											tự động</a></span>
									<form:input class="form-control" type="text" path="code"
										id="code" required="required"
										value="${ empty oldvalue.code ? '': oldvalue.code }" />
									<c:if test="${ not empty msgTitle}">
										<span class="form-error">${msgTitle}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Số lượng mã</label>
									<form:input class="form-control" type="number" path="available"
										required="required"
										value="${ empty oldvalue.available ? '': oldvalue.available }" />
								</div>
								<div class="form-group">
									<label>Số tiền giảm</label>
									<form:input class="form-control" type="number" path="pricesale"
										required="required"
										value="${ empty oldvalue.pricesale ? '': oldvalue.pricesale }" />
								</div>
								<div class="form-group">
									<label>Trạng thái</label>
									<form:select name="status" class="form-control" path="status">
										<form:option value="2" label="Chưa xuất bản"></form:option>
										<form:option value="1" label="Xuất bản"></form:option>
									</form:select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Bắt đầu khuyến mãi</label> <input class="form-control"
										type="date" name="start"
										value="${ empty oldvalue.start ? '': oldvalue.start }" />
								</div>
								<div class="form-group">
									<label>Hết hạn khuyến mãi</label> <input class="form-control"
										type="date" name="end"
										value="${ empty oldvalue.end ? '': oldvalue.end }" />
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- /.card -->

			</section>
			<!-- /.content -->
		</div>
	</form:form>
	<script>
		function randomString() {
			var length = 12;
			var result = '';
			var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
			var charactersLength = characters.length;
			for (var i = 0; i < length; i++) {
				result += characters.charAt(Math.floor(Math.random()
						* charactersLength));
			}
			var input = document.getElementById('code');
			input.value = result;
			window.event.preventDefault();
		}
	</script>
</body>
</html>