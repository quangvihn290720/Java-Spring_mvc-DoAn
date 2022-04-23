<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
</head>
<body>
	<c:url var="save" value="/quan-tri/san-pham/save" />
	<c:url var="list" value="/quan-tri/san-pham" />
	<form:form modelAttribute="product" action="${save}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm sản phẩm
								mới</strong>
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
									<label>Tên sản phẩm</label>
									<form:input class="form-control" type="text" path="productname"
										required="required"
										value="${ empty oldvalue.productname ? '': oldvalue.productname }" />
									<c:if test="${ not empty msgTitle}">
										<span class="form-error">${msgTitle}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Slug</label>
									<form:input class="form-control" path="product_slug"
										required="required"
										value="${ empty oldvalue.product_slug ? '': oldvalue.product_slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label>Mô tả ngắn</label>
									<form:input name="title" class="form-control" type="text"
										path="productshortdesc" required="required"
										value="${ empty oldvalue.productshortdesc ? '': oldvalue.productshortdesc }" />
								</div>
								<div class="form-group">
									<label>Chi tiết sản phẩm</label>
									<textarea name="productdetail" id="productdetail"
										class="form-control">${ empty oldvalue.productdetail ? '': oldvalue.productdetail }</textarea>
								</div>
								<div class="form-group">
									<label for="">Bảo hành</label>
									<form:input name="product_guarantee" class="form-control"
										type="text" placeholder="" path="product_guarantee"
										required="required"
										value="${ empty oldvalue.product_guarantee ? '': oldvalue.product_guarantee }" />
								</div>
								<div class="form-group">
									<label for="">Tình trạng</label>
									<form:input name="	product_condition" class="form-control"
										type="text" placeholder="" path="product_condition"
										required="required"
										value="${ empty oldvalue.product_condition ? '': oldvalue.product_condition }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label>
									<form:select name="product_status" class="form-control"
										path="product_status">
										<form:option value="1" label="Xuất bản"></form:option>
										<form:option value="2" label="Chưa xuất bản"></form:option>
									</form:select>
								</div>
								<div class="form-group">
									<label>Số lượng</label>
									<form:input class="form-control" type="number"
										path="product_available" required="required"
										value="${ empty oldvalue.product_available ? '': oldvalue.product_available }" />
								</div>
								<div class="form-group">
									<label>Giá gốc</label>
									<form:input class="form-control" type="text"
										path="productprice" required="required"
										value="${ empty oldvalue.productprice ? '': oldvalue.productprice }" />
								</div>
								<div class="form-group">
									<label>Giá bán</label>
									<form:input class="form-control" type="text"
										path="productpricesale" required="required"
										value="${ empty oldvalue.productpricesale ? '': oldvalue.productpricesale }" />
								</div>
								<div class="form-group">
									<label>Trọng lượng</label>
									<form:input class="form-control" type="text"
										path="productweight" required="required"
										value="${ empty oldvalue.productweight ? '': oldvalue.productweight }" />
								</div>
								<div class="form-group">
									<label>Loại</label>
									<c:if test="${cate != null }">
										<form:select name="status" class="form-control"
											path="product_catid">
											<c:forEach var="item" items="${cate}">
												<form:option value="${item.id}" label="${item.name}"></form:option>
											</c:forEach>
										</form:select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Nhà sản xuất</label>
									<c:if test="${manu != null }">
										<form:select name="status" class="form-control"
											path="manufacturer_id">
											<c:forEach var="item" items="${manu}">
												<form:option value="${item.manufacturer_id}"
													label="${item.manufacturer_name}"></form:option>
											</c:forEach>
										</form:select>
									</c:if>
								</div>
								<div class="form-group">
									<label>Hình ảnh</label> <input type="file"
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
</body>
</html>