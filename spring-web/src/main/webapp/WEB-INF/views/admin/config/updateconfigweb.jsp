<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cấu hình website</title>
</head>
<body>
	<c:url var="editsave" value="/quan-tri/web/cau-hinh-web/editsave" />
	<c:url var="list" value="/quan-tri/web/cau-hinh-web/" />
	<form:form modelAttribute="config" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Cấu hình
								website</strong>
						</h3>
						<div class="card-tools">
							<button type="submit"
								onclick="return confirm('Bạn có chắc chắn thực hiện không?');"
								class="btn btn-sm btn-success">
								<i class="fas fa-save"></i> Lưu
							</button>
							<a class="btn btn-sm btn-danger" href="${list }"> Quay về</a>
						</div>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-md-9">
								<form:input type="hidden" path="id" />
								<div class="form-group">
									<label>Tên website</label>
									<form:input class="form-control" type="text" path="nameweb"
										required="required" value="${config.nameweb}" />
								</div>
								<div class="form-group">
									<label>Mô tả</label>
									<form:input name="title" class="form-control" type="text"
										path="web_detail" value="${config.web_detail }" />
								</div>
								<div class="form-group">
									<label>Hotline</label>
									<form:input name="title" class="form-control" type="text"
										path="hotline" value="${config.hotline }" />
								</div>
								<div class="form-group">
									<label>Email</label>
									<form:input name="title" class="form-control" type="text"
										path="email" value="${config.email }" />
								</div>
								<div class="form-group">
									<label>Địa chỉ</label>
									<form:input name="title" class="form-control" type="text"
										path="address_store" value="${config.address_store }" />
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Logo website</label> <input type="file"
										class="form-control-file" accept="image/*" name="image" />
								</div>
								<div class="form-group">
									<label>Logo mobile</label> <input type="file"
										class="form-control-file" accept="image/*" name="image1" />
								</div>
								<div class="form-group">
									<label>Icon website</label> <input type="file"
										class="form-control-file" accept="image/*" name="image2" />
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
</body>
</html>