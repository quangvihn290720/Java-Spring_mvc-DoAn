<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm tùy chọn sản phẩm</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/tuy-chon-san-pham/save" />
	<c:url var="list" value="/quan-tri/tuy-chon-san-pham" />
	<form:form modelAttribute="prodop" action="${save}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm tùy chọn
								sản phẩm</strong>
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
									<label>Tên sản phẩm</label>
									<c:if test="${product != null }">
										<form:select class="form-control" path="product_id">
											<c:forEach var="item" items="${product}">
												<option value="${item.product_id}"
													label="${item.productname}"></option>
											</c:forEach>
										</form:select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Tùy chọn</label>
									<c:if test="${option != null }">
										<form:select class="form-control" path="option_id">
											<c:forEach var="item" items="${option}">
												<form:option value="${item.options_id}"
													label="${item.optionname}"></form:option>
											</c:forEach>
										</form:select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<form:textarea name="metadesc" id="metadesc" path="metadesc"
										class="form-control" rows="3" required="required"></form:textarea>
								</div>
								<div class="form-group">
									<label>Từ khóa</label>
									<form:input name="title" class="form-control" type="text"
										path="metakey" required="required" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label>
									<form:select name="product_status" class="form-control"
										path="status">
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