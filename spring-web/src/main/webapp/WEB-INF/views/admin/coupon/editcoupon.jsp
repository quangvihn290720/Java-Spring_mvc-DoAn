<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa chủ đề</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/web/ma-khuyen-mai/editsave" />
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
									<label>Mã khuyến mãi</label>
									<form:input class="form-control" type="text" path="code"
										required="required" style="text-transform:uppercase"
										value="${ empty oldvalue.code ? '': oldvalue.code }" />
									<c:if test="${ not empty msgTitle}">
										<span class="form-error">${msgTitle}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Số lượng mã</label>
									<form:input class="form-control" type="number" path="available"
										required="required" />
								</div>
								<div class="form-group">
									<label>Số lượng mã</label>
									<form:input class="form-control" type="number" path="pricesale"
										required="required" />
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
										type="date" name="start" value="${ couponitem.start}" />
								</div>
								<div class="form-group">
									<label>Hết hạn khuyến mãi</label> <input class="form-control"
										type="date" name="end" value="${ couponitem.end}" />
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

</body>
</html>