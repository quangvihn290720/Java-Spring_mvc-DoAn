<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm nhóm tùy chọn</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/nhom-tuy-chon/save" />
	<c:url var="list" value="/quan-tri/nhom-tuy-chon" />
	<form:form modelAttribute="opgroup" action="${save}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm nhóm tùy
								chọn</strong>
						</h3>
						<div class="card-tools">
							<button type="submit" onclick="return confirm('Bạn có chắc chắn thực hiện không?');" class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list}"> Quay về danh
								sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên nhóm</label>
									<form:input class="form-control" type="text"
										path="optiongroupname" required="required"
										value="${ empty oldvalue.optiongroupname ? '': oldvalue.optiongroupname }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<textarea class="form-control" id="metadesc" name="metadesc"
										required="required">${ empty oldvalue.metadesc ? '':oldvalue.metadesc}</textarea>
								</div>
								<div class="form-group">
									<label for="">Từ khóa</label>
									<form:input name="metakey" class="form-control" type="text"
										placeholder="Nhập tên" path="metakey" required="required"
										value="${ empty oldvalue.metakey ? '': oldvalue.metakey }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label>
									<form:select name="status" class="form-control" path="status">
										<form:option value="2" label="Chưa xuất bản"></form:option>
										<form:option value="1" label="Xuất bản"></form:option>
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
</body>
</html>