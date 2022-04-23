<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa sản phẩm</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/san-pham/editsave" />
	<c:url var="list" value="/quan-tri/san-pham" />
	<form:form modelAttribute="product" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Chỉnh sửa sản
								phẩm</strong>
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
							<form:hidden path="product_id" value="${prodItem.product_id}" />
							<div class="col-md-9">
								<div class="form-group">
									<label>Tên sản phẩm</label>
									<form:input class="form-control" type="text" path="productname"
										required="required"
										value="${ empty oldvalue.productname ? prodItem.productname: oldvalue.productname }" />
									<c:if test="${ not empty msgTitle}">
										<span class="form-error">${msgTitle}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Slug</label>
									<form:input name="title" class="form-control" type="text"
										path="product_slug" required="required"
										value="${ empty oldvalue.product_slug ? prodItem.product_slug: oldvalue.product_slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả ngắn</label>
									<form:input name="title" class="form-control" type="text"
										path="productshortdesc" required="required"
										value="${ empty oldvalue.productshortdesc ? prodItem.productshortdesc: oldvalue.productshortdesc }" />
								</div>
								<div class="form-group">
									<label>Chi tiết sản phẩm</label>
									<textarea name="productdetail" id="productdetail"
										class="form-control" rows="3">${ empty oldvalue.productdetail ? prodItem.productdetail: oldvalue.productdetail }</textarea>
								</div>
								<div class="form-group">
									<label for="">Bảo hành</label>
									<form:input name="product_guarantee" class="form-control"
										type="text" path="product_guarantee" required="required"
										value="${ empty oldvalue.product_guarantee ? prodItem.product_guarantee: oldvalue.product_guarantee }" />
								</div>
								<div class="form-group">
									<label for="">Tình trạng</label>
									<form:input name="product_condition" class="form-control"
										type="text" path="product_condition" required="required"
										value="${ empty oldvalue.product_condition ? prodItem.product_condition: oldvalue.product_condition }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label> <input type="hidden" id="status"
										value="${prodItem.product_status }">
									<form:select class="form-control" path="product_status">
										<form:option value="1" label="Xuất bản"></form:option>
										<form:option value="2" label="Chưa xuất bản"></form:option>
									</form:select>
								</div>
								<div class="form-group">
									<label>Loại</label>
									<form:input type="hidden" path="product_catid"
										value="${prodItem.product_catid}" />
									<c:if test="${cate != null }">
										<select name="" class="form-control" id="cateseclet">
											<c:forEach var="item" items="${cate}">
												<option value="${item.id}" label="${item.name}"></option>
											</c:forEach>
										</select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Nhà sản xuất</label>
									<form:input type="hidden" path="manufacturer_id"
										value="${prodItem.manufacturer_id}" />
									<c:if test="${manu != null }">
										<select name="" class="form-control" id="manufacturerseclet">
											<c:forEach var="item" items="${manu}">
												<option value="${item.manufacturer_id}"
													label="${item.manufacturer_name}"></option>
											</c:forEach>
										</select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Số lượng</label>
									<form:input class="form-control" type="number"
										path="product_available" required="required"
										value="${ empty oldvalue.product_available ? prodItem.product_available: oldvalue.product_available }" />
								</div>
								<div class="form-group">
									<label>Giá gốc</label>
									<form:input class="form-control" type="text"
										path="productprice" required="required"
										value="${ empty oldvalue.productprice ? prodItem.productprice: oldvalue.productprice }" />
								</div>
								<div class="form-group">
									<label>Giá bán</label>
									<form:input class="form-control" type="text"
										path="productpricesale" required="required"
										value="${ empty oldvalue.productpricesale ? prodItem.productpricesale: oldvalue.productpricesale }" />
								</div>
								<div class="form-group">
									<label>Trọng lượng</label>
									<form:input class="form-control" type="text"
										path="productweight" required="required"
										value="${ empty oldvalue.productweight ? prodItem.productweight: oldvalue.productweight }" />
								</div>
								<div class="form-group">
									<label>Hình đại diện</label> <input type="file"
										class="form-control-file" accept="image/*" name="image" />
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
		var status = document.getElementById("status").value;
		if (status == 2) {
			var list = document.getElementById("product_status").lastElementChild.selected = true;
		}
	</script>
	<script type="text/javascript">
		CKEDITOR
				.replace(
						'productdetail',
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
		var cateid = document.getElementById("product_catid").value;
		$("#cateseclet").val(cateid);

		$('#cateseclet').on('change', function() {
			$("#product_catid").val(this.value);
		});
	</script>
	<script type="text/javascript">
		var manuid = document.getElementById("manufacturer_id").value;
		$("#manufacturerseclet").val(manuid);

		$('#manufacturerseclet').on('change', function() {
			$("#manufacturer_id	").val(this.value);
		});
	</script>
</body>
</html>