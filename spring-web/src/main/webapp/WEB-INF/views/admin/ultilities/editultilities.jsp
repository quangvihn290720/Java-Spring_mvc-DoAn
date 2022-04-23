<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật tiện ích</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/tien-ich/editsave" />
	<c:url var="list" value="/quan-tri/tien-ich" />
	<form:form modelAttribute="utilities" action="${save}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cập nhật tiện
								ích</strong>
						</h3>
						<div class="card-tools">
							<button type="submit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list}"> Quay về danh
								sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<form:hidden path='utilities_id' />
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên sản phẩm</label>
									<form:input class="form-control" type="hidden"
										path="product_id" value="${utilEntity.product_id}" />
									<input class="form-control" type="text"
										value="${mapProd[utilEntity.product_id]} " readonly="readonly" />
								</div>
								<div class="form-group">
									<label>Tiện ích</label>
									<form:input name="title" class="form-control" type="text"
										path="utilitiesname" required="required"
										value="${ empty oldvalue.utilitiesname ? utilEntity.utilitiesname: oldvalue.utilitiesname }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<textarea name="metadesc" id="metadesc" class="form-control"
										required="required">${ empty oldvalue.metadesc ? utilEntity.metadesc:oldvalue.metadesc}</textarea>
								</div>
								<div class="form-group">
									<label>Từ khóa</label>
									<form:input name="title" class="form-control" type="text"
										path="metakey" required="required"
										value="${ empty oldvalue.metakey ? utilEntity.metakey:oldvalue.metakey}" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label> <input type="hidden" id="status123"
										value="${utilEntity.status}">
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
		CKEDITOR
				.replace(
						'metadesc',
						{
							filebrowserBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html',
							filebrowserImageBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html?type=Images',
							filebrowserFlashBrowseUrl : '${pageContext.request.contextPath}/libraries/ckfinder/ckfinder.html?type=Flash',
							filebrowserUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
							filebrowserImageUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
							filebrowserFlashUploadUrl : '${pageContext.request.contextPath}/libraries/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash',
							filebrowserWindowWidth : '1000',
							filebrowserWindowHeight : '700'
						});
	</script>
	<script type="text/javascript">
		var product_id = document.getElementById("product_id").value;
		$("#productseclet").val(product_id);

		$('#productseclet').on('change', function() {
			$("#product_id").val(this.value);
		});
	</script>
</body>
</html>