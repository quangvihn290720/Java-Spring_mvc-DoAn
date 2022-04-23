package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ICouponService {
	public List<CouponEntity> findAllCoupon();

	public List<CouponEntity> findAllCouponShow();

	public void add(CouponEntity coupon);

	public void update(CouponEntity coupon);

	public void delete(int id);

	public CouponEntity findCouponById(CouponEntity coupon);

	public List<CouponEntity> findTrashCoupon();

	public void deltrash(int id, UserEntity loginInfo);

	public void retrash(int id, UserEntity loginInfo);

	public void onOffCoupon(int id, UserEntity loginInfo);

	public List<CouponEntity> GetDataCouponPaginate(int start, int totalPage);

	public List<CouponEntity> GetDataCouponTrashPaginate(int start, int totalPage);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);

	public List<CouponEntity> findCouponByCode(String code);

	public CouponEntity checkAvailCode(String code);

	public void subVailable(String code);

	public CouponEntity findCouponById(int id);

}
