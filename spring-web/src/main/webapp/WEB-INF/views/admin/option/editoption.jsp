<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa tùy chọn</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/tuy-chon/editsave" />
	<c:url var="list" value="/quan-tri/tuy-chon" />
	<form:form modelAttribute="option" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Chỉnh sửa tùy
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
							<form:hidden path="options_id" />
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên nhóm</label>
									<form:input type="hidden" path="optiongroup_id"
										value="${options.optiongroup_id}" />
									<c:if test="${opgroup != null }">
										<select class="form-control" id="opgroupseclet">
											<c:forEach var="item" items="${opgroup}">
												<option value="${item.optiongroups_id}"
													label="${item.optiongroupname}"></option>
											</c:forEach>
										</select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Tên tùy chọn</label>
									<form:input class="form-control" type="text" path="optionname"
										required="required"
										value="${ empty oldvalue.optionname ? options.optionname: oldvalue.optionname }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<textarea name="metadesc" id="metadesc" class="form-control"
										 required="required">${ empty oldvalue.metadesc ? options.metadesc: oldvalue.metadesc }</textarea>
								</div>
								<div class="form-group">
									<label for="">Từ khóa</label>
									<form:input name="metakey" class="form-control" type="text"
										placeholder="Nhập tên" path="metakey" required="required"
										value="${ empty oldvalue.metakey ? options.metakey: oldvalue.metakey }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label><input type="hidden" id="status123"
										value="${options.status}">
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
			document.getElementById("status").lastElementChild.selected = true;
		}
		var topicid = document.getElementById("optiongroup_id").value;
		$("#opgroupseclet").val(topicid);

		$('#opgroupseclet').on('change', function() {
			$("#optiongroup_id").val(this.value);
		});
	</script>
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