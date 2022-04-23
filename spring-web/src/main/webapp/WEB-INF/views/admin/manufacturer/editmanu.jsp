<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật hãng sản xuất</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/hang/editsave" />
	<c:url var="list" value="/quan-tri/hang" />
	<form:form modelAttribute="manufacturer" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cập nhật hãng
								sản xuất</strong>
						</h3>
						<div class="card-tools">
							<button type="submit" onclick="return confirm('Bạn có chắc chắn thực hiện không?');" class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list }"> Quay về
								danh sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<form:input type="hidden" path="manufacturer_id" />
								<div class="form-group">
									<label>Tên hãng sản xuất</label>
									<c:if test="${not empty manufactureritem}">
										<form:input class="form-control" type="text"
											path="manufacturer_name" required="required"
											value="${ empty oldvalue.manufacturer_name ? manufactureritem.manufacturer_name: oldvalue.manufacturer_name }" />
										<c:if test="${ not empty msgTitle}">
											<span class="form-error">${msgTitle}</span>
										</c:if>
									</c:if>
								</div>
								<div class="form-group">
									<label>Slug</label>
									<form:input name="title" class="form-control" type="text"
										path="manufacturer_slug" required="required"
										value="${ empty oldvalue.manufacturer_slug ? manufactureritem.manufacturer_slug: oldvalue.manufacturer_slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Hình đại diện</label> <input type="file"
										class="form-control-file" accept="image/*" name="image" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label> <input type="hidden" id="status123"
										value="${manufactureritem.status }">
									<form:select class="form-control" path="status">
										<form:option value="1" label="Xuất bản"></form:option>
										<form:option value="2" label="Chưa xuất bản" />
									</form:select>
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
		var status = document.getElementById("status123").value;
		if (status == 2) {
			var list = document.getElementById("status").lastElementChild.selected = true;

		}
	</script>
</body>
</html>