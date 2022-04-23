<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa danh mục</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/danh-muc/save" />
	<c:url var="list" value="/quan-tri/danh-muc" />
	<form:form modelAttribute="category" action="${save}" method="POST"
		enctype="multipart/form-data">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cập nhật danh
								mục</strong>
						</h3>
						<div class="card-tools">
							<button type="submit" onclick="return confirm('Bạn có chắc chắn thực hiện không?');" class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list}">Quay về danh
								sách</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<form:hidden path="id" />
								<div class="form-group">
									<label>Tên trang đơn</label>
									<form:input class="form-control" type="text" path="name"
										required="required"
										value="${ empty oldvalue.name ? '': oldvalue.name }" />
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
									<form:input class="form-control" type="text"
										placeholder="Nhập tên" path="metakey" required="required"
										value="${ empty oldvalue.metakey ? '': oldvalue.metakey }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Slug</label>
									<form:input class="form-control" type="text" path="slug"
										required="required"
										value="${ empty oldvalue.slug ? '': oldvalue.slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Trạng thái</label>
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
	<script type="text/javascript">
		/* 		var editor = CKEDITOR.replace('desctextarea');
		 CKFinder.setupCKEditor(editor,
		 '${pageContext.request.contextPath}/template/admin/ckfinder/'); */
		CKEDITOR
				.replace(
						'metadesc',
						{
							filebrowserBrowseUrl : '${pageContext.request.contextPath}/template/admin/ckfinder/ckfinder.html',
							filebrowserWindowWidth : '1000',
							filebrowserWindowHeight : '700'
						});
	</script>
</body>
</html>