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
	<c:url var="editsave" value="/quan-tri/web/menu/editsave" />
	<c:url var="list" value="/quan-tri/web/menu" />
	<form:form modelAttribute="menu" action="${editsave}"
		enctype="multipart/form-data" method="POST">
		<div class="content-wrapper pt-3">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content">
				<!-- Default box -->
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<strong class="text-uppercase text-danger">Thêm tùy chọn</strong>
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
									<form:input type="hidden" path="menu_id"
										value="${menuitem.menu_id}" />
									<label>Tên menu</label>
									<form:input class="form-control" type="text" path="menu_name"
										required="required"
										value="${ empty oldvalue.menu_name ? menuitem.menu_name: oldvalue.menu_name }" />
									<c:if test="${ not empty msgName}">
										<span class="form-error">${msgName}</span>
									</c:if>
								</div>
								<div class="form-group">
									<label for="">Slug</label>
									<form:input class="form-control" type="text"
										placeholder="Nhập tên" path="menu_slug" required="required"
										value="${ empty oldvalue.menu_slug ? menuitem.menu_slug: oldvalue.menu_slug }" />
									<c:if test="${ not empty msgSlug}">
										<span class="form-error">${msgSlug}</span>
									</c:if>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group">
									<label>Trạng thái</label><input type="hidden" id="status123"
										value="${menuitem.menu_status}">
									<form:select name="status" class="form-control"
										path="menu_status">
										<form:option value="2" label="Chưa xuất bản"></form:option>
										<form:option value="1" label="Xuất bản"></form:option>
									</form:select>
								</div>
								<div class="form-group">
									<label>Cấp cha</label> <select name="parent_id"
										class="form-control" id="parent_id">
										<option value="0">...</option>
										<c:forEach var="item" items="${ listmenu }">
											<option value="${item.menu_id }"
												${item.menu_id == menuitem.parent_id?"selected":"" }>${item.menu_name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>Thứ tự</label> <select name="orders"
										class="form-control" id="orders">
									</select>
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
		if (status == 1) {
			document.getElementById("menu_status").lastElementChild.selected = true;
		}
	</script>
	<script>
	$(document).ready(function () {
		var id = ${menuitem.parent_id};
		var orders =  ${menuitem.orders}
		var parent = ${menuitem.parent_id}
		$.ajax({
		 	url: "http://localhost:8080/spring-web/quan-tri/web/menu/parent/" + id ,

 	        method: "GET",
 	        dataType: 'json',
 	        success:function(data) {
 	        	$("#orders").html('<option value="0">---</options>');
 	            $.each(data, function(key, val) {
 	            	if(val.orders == (orders-1) && parent == val.parent_id){
 	            		$("#orders").append('<option value="' + val.orders + '" selected>' +"Sếp sau " + val.menu_name + '</option>');	 	            		
 	            	}
 	            	else{
 	            		$("#orders").append('<option value="' + val.orders + '">' +"Sếp sau " + val.menu_name + '</option>');
 	            	}

 	            })
 	        },
 	        error: function(){
 	            console.log("sai2");
 	        }
    	});
		
		
		
		
		
		 $('#parent_id').change(()=>{    
		    	$("#orders").empty();
				var pi = $("#parent_id").val();
		    	 if($("#parent_id").val()!=""){
		 	    	$.ajax({
		    		 	url: "http://localhost:8080/spring-web/quan-tri/web/menu/parent/" + pi,
			
			 	        method: "GET",
			 	        dataType: 'json',
			 	        success:function(data) {
			 	        	$("#orders").html('<option value="0">---</options>');
			 	            $.each(data, function(key, val) {
				 	            	$("#orders").append('<option value="' + val.orders + '">' +"Sếp sau " + val.menu_name + '</option>');	 	            		

			 	            })
			 	        },
			 	        error: function(){
			 	            console.log("sai2");
			 	        }
			    	});
		    	 }

			});
	});
	</script>
</body>
</html>