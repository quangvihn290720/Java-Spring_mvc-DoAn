<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa banner quảng cáo (topbar)</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/web/thanh-tren/editsave" />
	<c:url var="list" value="/quan-tri/web/thanh-tren" />
	<form:form modelAttribute="topbar" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cập nhật ảnh
								bìa</strong>
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
								<form:hidden path="id" />
								<div class="form-group">
									<label>Tên chủ đề</label>
									<c:if test="${not empty topbaritem}">
										<form:input class="form-control" type="text" path="name"
											required="required"
											value="${ empty oldvalue.name ? topbaritem.name: oldvalue.name }" />
										<c:if test="${ not empty msgName}">
											<span class="form-error">${msgName}</span>
										</c:if>
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
										value="${topbaritem.status}">
									<form:select class="form-control" path="status">
										<form:option value="1" label="Xuất bản"></form:option>
										<form:option value="2" label="Chưa xuất bản"></form:option>
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
	<script type="text/javascript">
		var status = document.getElementById("status123").value;
		if (status == 2) {
			var list = document.getElementById("status").lastElementChild.selected = true;
		}
	</script>
</body>
</html>