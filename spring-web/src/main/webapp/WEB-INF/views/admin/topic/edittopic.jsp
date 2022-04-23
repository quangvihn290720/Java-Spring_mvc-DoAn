<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa chủ đề</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/chu-de/editsave" />
	<c:url var="list" value="/quan-tri/chu-de" />
	<form:form modelAttribute="topic" action="${editsave}" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cập nhật chủ
								đề</strong>
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
								<form:hidden path="topic_id" />
								<div class="form-group">
									<label>Tên chủ đề</label>
									<form:input class="form-control" type="text" path="topic_name"
										required="required"
										value="${ empty oldvalue.topic_name ? topicitem.topic_name: oldvalue.topic_name }" />
									<c:if test="${ not empty msgTitle}">
										<span class="form-error">${msgTitle}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Slug</label>
									<form:input class="form-control" type="text" path="topic_slug"
										required="required"
										value="${ empty oldvalue.topic_slug ? topicitem.topic_slug: oldvalue.topic_slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<textarea class="form-control" id="topic_metadesc"
										name="topic_metadesc" required="required">${ empty oldvalue.topic_metadesc ? topicitem.topic_metadesc:oldvalue.topic_metadesc}</textarea>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label> <input type="hidden" id="status123"
										value="${topicitem.topic_status}">
									<form:select name="status" class="form-control"
										path="topic_status">
										<form:option value="1" label="Xuất bản"></form:option>
										<form:option value="2" label="Chưa xuất bản"></form:option>
									</form:select>
								</div>
								<div class="form-group">
									<label for="">Từ khóa</label>
									<form:input name="metakey" class="form-control" type="text"
										required="required" placeholder="Nhập tên"
										path="topic_metakey" value="${topicitem.topic_metakey}" />
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
			var list = document.getElementById("topic_status").lastElementChild.selected = true;
		}
	</script>
	F
	<script type="text/javascript">
		CKEDITOR
				.replace(
						'topic_metadesc',
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