<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa quyền nhân vật</title>
</head>
<body>
	<c:url var="save"
		value="/quan-tri/web/tai-khoan/cap-nhat-quyen/editsave" />
	<c:url var="list" value="/quan-tri/web/tai-khoan/" />
	<form:form modelAttribute="user" action="${save}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Sửa quyền
								nhân vật</strong>
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
							<form:hidden path='user_id' value="${useritem.user_id}" />
							<div class="col-md-9">
								<div class="form-group">
									<label>Vai trò chính</label> <input type="hidden" id="role1"
										value="${useritem.role}">
									<form:select class="form-control" path="role">
										<form:option value="ROLE_USER" label="Người dùng"></form:option>
										<form:option value="ROLE_ADMIN" label="Người quản trị"></form:option>
									</form:select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Vai trò phụ</label>
									<form:input type="hidden" path="role2"
										value="${useritem.role2}" />
									<c:if test="${listrole != null }">
										<select name="status" class="form-control" id="topicseclet">
											<c:forEach var="item" items="${listrole}">
												<option value="${item.id}" label="${item.name}"></option>
											</c:forEach>
										</select>
									</c:if>
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
		var role = document.getElementById("role1").value;
		if (role == 'ROLE_ADMIN') {
			var list = document.getElementById("role").lastElementChild.selected = true;
		}
	</script>
	<script type="text/javascript">
		var topicid = document.getElementById("role2").value;
		$("#topicseclet").val(topicid);

		$('#topicseclet').on('change', function() {
			$("#role2").val(this.value);
		});
	</script>
</body>
</html>