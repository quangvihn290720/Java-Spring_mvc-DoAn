package com.khoaluantotnghiep.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.service.impl.CouponServiceImpl;

@Controller(value = "couponControllerOfWeb")
public class CouponController extends BaseController {
	@Autowired
	CouponServiceImpl couponService;
	
	@RequestMapping(value = "/ma-khuyen-mai/check/{coupon_code}", method = RequestMethod.GET)
	public @ResponseBody List<CouponEntity> findCouponByCode(@PathVariable String coupon_code) {
	    return couponService.findCouponByCode(coupon_code);
	}
}
