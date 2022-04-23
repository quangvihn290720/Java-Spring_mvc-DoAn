<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
	<header class="banner">
		<div class="wrap">
			<div class="logo-center">
				<h1 class="shop_name">Thành Vinh Shop</h1>
			</div>
		</div>
	</header>
	<aside>
		<button class="order-summary-toggle" data-toggle="#order-summary"
			data-toggle-class="order-summary--is-collapsed">
			<span class="wrap"> <span class="order-summary-toggle__inner">
					<span class="order-summary-toggle__text expandable"> Đơn
						hàng ( <c:choose>
							<c:when test="${not empty Cart}">${TotalQuantyCart}
								</c:when>
							<c:otherwise>0
								</c:otherwise>
						</c:choose> sản phẩm)
				</span> <span class="order-summary-toggle__total-recap">65.000.000đ</span>
			</span>
			</span>
		</button>
	</aside>
	<div class="content">
		<c:url var="checkout" value="/checkout" />
		<form:form id="checkoutForm" modelAttribute="bills"
			action="${checkout}" method="post">
			<div class="wrap">
				<div class="main-co">
					<div class="main-co-header">
						<div class="logo logo-center">
							<h1 class="shop_name">Thành Vinh Shop</h1>
						</div>
					</div>
					<div class="main-co-content">
						<article class=" row">
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title layout-flex-item ">Thông tin
												nhận hàng</h2>
										</div>
									</div>
									<div class="section-co-content">
										<div class="fieldset ">
											<div
												class="field ${empty bill.email?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="email" class="field-label"> Email </label>
													<form:input name="email" id="email" type="email"
														class="field-input" path="email" data-bind="email"
														value="${bill.email}" required="required" />
												</div>
											</div>
											<div
												class="field ${empty bill.display_name?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="billingName" class="field-label">Họ và
														tên</label>
													<form:input name="billingName" id="billingName" type="text"
														class="field-input" value="${bill.display_name}"
														path="display_name" required="required" />
												</div>

											</div>

											<div
												class="field ${empty bill.phone?'':'field-show-floating-label'}">
												<div class="field-input-wrapper">
													<label for="billingPhone" class="field-label"> Số
														điện thoại (tùy chọn) </label>
													<form:input name="billingPhone" id="billingPhone"
														type="tel" class="field-input" value="${bill.phone}"
														path="phone" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"
														title="Số điện thoại sai định dạng" required="required" />
												</div>

											</div>


											<div class="field">
												<div class="field-input-wrapper">
													<label for="billingAddress" class="field-label">
														Địa chỉ (tùy chọn) </label>
													<form:input name="billingAddress" id="billingAddress"
														type="text" class="field-input" value="" path="address"
														required="required" />
												</div>

											</div>


											<div class="field field-show-floating-label ">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingProvince" class="field-label">Tỉnh
														thành</label> <select name="billingProvince" id="billingProvince"
														size="1" onChange="myNewFunction(this);"
														class="field-input field-input--select"
														required="required">
													</select>
													<form:input type="hidden" path="city" id="city" />
												</div>

											</div>

											<div class="field field-show-floating-label">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingDistrict" class="field-label">
														Quận huyện (tùy chọn) </label> <select name="billingDistrict"
														id="billingDistrict" size="1"
														onChange="myNewFunction1(this);"
														class="field-input field-input--select disabled-select">
													</select>
													<form:input type="hidden" path="district" id="district" />
												</div>
											</div>
											<div class="field field-show-floating-label">
												<div
													class="field-input-wrapper field-input-wrapper--select2">
													<label for="billingWard" class="field-label">
														Phường xã (tùy chọn) </label> <select name="billingWard"
														id="billingWard" size="1"
														class="field-input field-input--select disabled-select"
														onChange="myNewFunction2(this);">
													</select>
													<form:input type="hidden" path="province" id="province" />
												</div>
											</div>
										</div>
									</div>
								</section>

								<div class="fieldset">
									<div class="field ">
										<div class="field-input-wrapper">
											<label for="note" class="field-label"> Ghi chú (tùy
												chọn) </label>
											<form:textarea name="note" id="note" type="text"
												class="field-input" path="note"></form:textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="col-full col-half">
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2 class="section-co-title">Vận chuyển</h2>
										</div>
									</div>
									<div class="section-co-content">
										<div class="content-box">
											<div class="content-box-row">
												<div class="radio-wrapper">
													<div class="radio-input">
														<input type="radio" class="input-radio"
															name="shippingMethod" id="shippingMethod1" checked>
													</div>
													<label for="shippingMethod1" class="radio-label"> <span
														class="radio-label-primary">Giao hàng tận nơi</span> <span
														class="radio-label-accessory"> <span
															class="content-box-emphasis"> 40.000₫ </span>
													</span>
													</label>
												</div>
											</div>

										</div>

									</div>
								</section>
								<section class="section">
									<div class="section-co-header">
										<div class="layout-flex">
											<h2
												class="section-co-title layout-flex-item layout-flex-item--stretch">
												Thanh toán</h2>
										</div>
									</div>
									<div class="section__content">
										<div class="content-box">

											<div class="content-box-row">
												<div class="radio-wrapper">
													<div class="radio-input">
														<input name="paymentMethod" id="paymentMethod-491087"
															type="radio" class="input-radio" data-bind=""
															value="491087" checked>
													</div>
													<label for="paymentMethod-491087" class="radio-label">
														<span class="radio-label-primary">Thanh toán khi
															giao hàng (COD)</span> <span class="radio-label-accessory">
															<span class="radio__label__icon"> <i
																class="fas fa-money-bill-wave"></i>
														</span>
													</span>

													</label>
												</div>
												<div class="radio-wrapper">
													<button type="submit"></button>
												</div>
											</div>
										</div>
									</div>
								</section>
							</div>
						</article>
						<div
							class="field__input-btn-wrapper field__input-btn-wrapper-vertical hide-on-desktop">
							<button type="submit" class="btn btn-info btn-checkout spinner">
								<span class="spinner-label">ĐẶT HÀNG</span>
							</button>

							<a href="/cart" class="previous-link"> <i
								class="fas fa-chevron-left"></i> <span
								class="previous-link__content">Quay về giỏ hàng</span>
							</a>

						</div>
					</div>
				</div>
				<aside class="sidebar-co">
					<div class="sidebar-co-header">
						<h2 class="sidebar-co-title">
							Đơn hàng (
							<c:choose>
								<c:when test="${not empty Cart}">${TotalQuantyCart}
								</c:when>
								<c:otherwise>0
								</c:otherwise>
							</c:choose>
							sản phẩm)
						</h2>
					</div>
					<div class="sidebar-co-content">
						<div id="order-summary"
							class="order-summary order-summary--is-collapsed">
							<div class="order-summary-sections">
								<div
									class="order-summary__section order-summary__section--product-list order-summary-scroll order-summary--collapse-element">
									<table class="product-table">
										<thead class="product-table-header">
											<tr>
												<th><span class="visually-hidden">Ảnh sản phẩm</span></th>
												<th><span class="visually-hidden">Mô tả</span></th>
												<th><span class="visually-hidden">Sổ lượng</span></th>
												<th><span class="visually-hidden">Đơn giá</span></th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty Cart }">
												<c:forEach var="item" items="${Cart}">
													<tr class="product">
														<td class="product__image">
															<div class="product-thumbnail">
																<div class="product-thumbnail__wrapper" data-tg-static>

																	<img
																		src="${pageContext.request.contextPath}/images/${item.value.productEntity.productimg}"
																		alt="" class="product-thumbnail__image">

																</div>
																<span class="product-thumbnail__quantity">${item.value.quanty}</span>
															</div>
														</td>
														<th class="product__description"><span
															class="product__description__name">${item.value.productEntity.productname}
														</span></th>
														<td class="product__quantity visually-hidden"><em>Số
																lượng:</em>${item.value.quanty}</td>
														<td class="product__price"><fmt:formatNumber
																value="${item.value.quanty * item.value.productEntity.productpricesale}"
																type="number" />đ</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
								<div
									class="order-summary__section order-summary__section--discount-code"
									id="discountCode">
									<div class="edit_checkout animate-floating-labels">
										<div class="fieldset">
											<div class="field  ">
												<div class="field-input-btn-wrapper">
													<div class="field-input-wrapper">
														<label for="reductionCode" class="field-label">Nhập
															mã giảm giá</label> <input name="reductionCode"
															id="reductionCode" type="text" class="field-input"
															autocomplete="off" style="margin: 0px">
													</div>
													<button
														class="field-input-btn btn btn-info spinner btn-apply"
														type="button">
														<span class="spinner-label">Áp dụng</span>
													</button>
												</div>
												<p class="text-danger" id="msg-error"></p>
												<p class="text-success" id="msg-success"></p>
												<!-- Xuat loi o day  vd ma da su dung, kh ton tai-->
											</div>
										</div>
									</div>
								</div>
								<div
									class="order-summary__section order-summary__section--total-lines"
									id="orderSummary">
									<table class="total-line-table">
										<caption class="visually-hidden">Tổng giá trị</caption>
										<thead>
											<tr>
												<td><span class="visually-hidden">Mô tả</span></td>
												<td><span class="visually-hidden">Giá tiền</span></td>
											</tr>
										</thead>
										<tbody class="total-line-table__tbody">
											<tr class="total-line total-line--subtotal">
												<th class="total-line__name">Tạm tính</th>
												<td class="total-line__price"><fmt:formatNumber
														value="${ TotalPriceCart }" type="number" />₫</td>
											</tr>

											<tr class="total-line total-line--shipping-fee">
												<th class="total-line__name">Phí vận chuyển</th>
												<td class="total-line__price">40,000đ</td>
											</tr>

										</tbody>
										<tfoot class="total-line-table__footer">
											<tr class="total-line payment-due">
												<th class="total-line__name"><span
													class="payment-due__label-total"> Tổng cộng </span></th>
												<td class="total-line__price"><span
													class="payment-due-price"><fmt:formatNumber
															value="${ TotalPriceCart + 40000 }" type="number" />₫</span></td>
											</tr>
										</tfoot>
									</table>
								</div>
								<div
									class="order-summary__nav field__input-btn-wrapper hide-on-mobile layout-flex--row-reverse">
									<button type="submit" class="btn btn-info btn-checkout spinner">
										<span class="spinner-label">ĐẶT HÀNG</span>
									</button>


									<a href="<c:url value="/gio-hang"/>" class="previous-link">
										<i class="fas fa-chevron-left"></i> <span
										class="previous-link__content">Quay về giỏ hàng</span>
									</a>

								</div>
							</div>
						</div>
					</div>
				</aside>
			</div>
		</form:form>
	</div>
	<script>
		function setInputFilter(textbox, inputFilter) {
			[ "input", "keydown", "keyup", "mousedown", "mouseup", "select",
					"contextmenu", "drop" ].forEach(function(event) {
				textbox.addEventListener(event, function() {
					if (inputFilter(this.value)) {
						this.oldValue = this.value;
						this.oldSelectionStart = this.selectionStart;
						this.oldSelectionEnd = this.selectionEnd;
					} else if (this.hasOwnProperty("oldValue")) {
						this.value = this.oldValue;
						this.setSelectionRange(this.oldSelectionStart,
								this.oldSelectionEnd);
					} else {
						this.value = "";
					}
				});
			});
		}
		setInputFilter(document.getElementById("billingPhone"), function(value) {
			return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
		});
	</script>
	<script>
		function myNewFunction(sel) {
			var sel = document.getElementById("billingProvince");
			var text1 = sel.options[sel.selectedIndex].text;
			document.getElementById("city").value =  text1;
		}
		function myNewFunction1(sel) {
			var sel = document.getElementById("billingDistrict");
			var text1 = sel.options[sel.selectedIndex].text;
			document.getElementById("district").value =  text1;
		}
		function myNewFunction2(sel) {
			var sel = document.getElementById("billingWard");
			var text1 = sel.options[sel.selectedIndex].text;
			document.getElementById("province").value =  text1;
		}
		function checkForInput(element) {

			var $label = $(element).parent().parent();
			if(!$(element).hasClass("field-input--select")){
				if ($(element).val().length > 0) {
					if (!$label.hasClass("field-show-floating-label")) {
						$label.addClass("field-show-floating-label");
					}
				} else {
					if ($label.hasClass("field-show-floating-label")) {
						$label.removeClass("field-show-floating-label");
					}
				}
			}
		}
		$('.field-input').on('change keyup', function() {
			checkForInput(this);
		});
	</script>
	<script>
	$(document).ready(function () {
		$select = $('#billingProvince');
	    $.ajax({
	        
	        url: 'template/web/assets/js/tinh_tp.json',

	        method: "GET",
	        dataType: 'json',
	        success:function(data) {
	            $select.html('<option value="">---</options>');
	            $.each(data, function(key, val) {
	                $select.append('<option value="' + val.code + '">' + ((val.type == "tinh") ? val.name : val.name_with_type) + '</option>');
	            })
	        },
	        error: function(){
	            console.log("sai1");
	        }
	    });
	    
	    
	    $('#billingProvince').change(()=>{    
	    	$("#billingDistrict").empty();
	    	$("#billingWard").empty();
	    	 if(!$("#billingDistrict").hasClass("disabled-select")){
	    		    $("#billingDistrict").addClass("disabled-select");
	    		 }
	    	 if(!$("#billingWard").hasClass("disabled-select")){
	    		    $("#billingWard").addClass("disabled-select");
	    		 }
	    	
	    	 if($("#billingProvince").val()!=""){
	 	    	$.ajax({
	    		 	url: 'template/web/assets/js/quan_huyen.json',
		
		 	        method: "GET",
		 	        dataType: 'json',
		 	        success:function(data) {
		 	        	$("#billingDistrict").html('<option value="">---</options>');
		 	        	var provinceId =$("#billingProvince").val();
		 	            $.each(data, function(key, val) {
		 	            	if(val.parent_code == provinceId){
			 	            	$("#billingDistrict").append('<option value="' + val.code + '">' + val.name_with_type + '</option>');	 	            		
		 	            	}

		 	            })
		 	           $("#billingDistrict").removeClass("disabled-select");
		 	        },
		 	        error: function(){
		 	            console.log("sai2");
		 	        }
		    	});
	    	 }

		});
		    $('#billingDistrict').change(()=>{    
	    	$("#billingWard").empty();
	    	 if(!$("#billingWard").hasClass("disabled-select")){
	    		    $("#billingWard").addClass("disabled-select");
	    		 };
	    		 if($("#billingDistrict").val()!=""){
	    		    	$.ajax({
	    	    		 	url: 'template/web/assets/js/xa_phuong.json',
	    		
	    		 	        method: "GET",
	    		 	        dataType: 'json',
	    		 	        success:function(data) {
	    		 	        	$("#billingWard").html('<option value="">---</options>');
	    		 	        	var districtId =$("#billingDistrict").val();
	    		 	            $.each(data, function(key, val) {
									if(val.parent_code == districtId){
										$("#billingWard").append('<option value="' + val.code + '">' + val.name_with_type + '</option>');
	    		 	            }
	    		 	            	
	    		 	            })
	    		 	            $("#billingWard").removeClass("disabled-select");
	    		 	        },
	    		 	        error: function(){
	    		 	            console.log("sai3");
	    		 	        }
	    		    	});
	    			 
	    		 }

		});
	})
	</script>
	<script>$(function () {
	    $('.btn-apply').on('click', function () {
	        var coupon = $('#reductionCode').val();
	        $.ajax({
	            url: "http://localhost:8080/spring-web/ma-khuyen-mai/check/" + coupon,
	            method: "GET",
	            dataType: 'json',
	            success:function(data) {
					if(data.length){
						$("#msg-success").text('Mã khả dụng, bạn sẽ được giảm '+data[0].pricesale.toLocaleString('it-IT', {style : 'currency', currency : 'VND'})+' vào hóa đơn');
						$("#msg-error").text('');
						 $("#reductionCode").attr("readonly", true);
					}
					else{
						$("#msg-success").text('');
						$("#msg-error").text('Mã không khả dụng');
					}
					
	 	        },
	 	        error: function(){
	 	            console.log("sai2");
	 	        }
	        });
	    });
	});</script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>