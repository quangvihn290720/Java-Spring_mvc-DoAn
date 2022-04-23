package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.CouponDAO;
import com.khoaluantotnghiep.entity.CouponEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ICouponService;

@Service
public class CouponServiceImpl implements ICouponService {
	@Autowired
	CouponDAO conponDao;

	@Override
	public List<CouponEntity> findAllCoupon() {
		return conponDao.findAllCoupon();
	}

	@Override
	public List<CouponEntity> findAllCouponShow() {
		return conponDao.findAllCouponShow();
	}

	@Override
	public void add(CouponEntity coupon) {

		conponDao.add(coupon);
	}

	@Override
	public void update(CouponEntity coupon) {
		conponDao.update(coupon);
	}

	@Override
	public void delete(int id) {
		conponDao.delete(id);
	}

	@Override
	public CouponEntity findCouponById(CouponEntity coupon) {
		return conponDao.findCouponById(coupon);
	}

	@Override
	public List<CouponEntity> findTrashCoupon() {
		return conponDao.findTrashCoupon();
	}

	@Override
	public void deltrash(int id, UserEntity loginInfo) {
		conponDao.deltrash(id, loginInfo);
	}

	@Override
	public void retrash(int id, UserEntity loginInfo) {
		conponDao.retrash(id, loginInfo);
	}

	@Override
	public void onOffCoupon(int id, UserEntity loginInfo) {
		conponDao.onOffCoupon(id, loginInfo);
	}

	@Override
	public List<CouponEntity> GetDataCouponPaginate(int start, int totalPage) {
		return conponDao.GetDataCouponPaginate(start, totalPage);
	}

	@Override
	public List<CouponEntity> GetDataCouponTrashPaginate(int start, int totalPage) {
		return conponDao.GetDataCouponTrashPaginate(start, totalPage);
	}

	@Override
	public boolean isNameExists(String name) {
		return conponDao.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return conponDao.isNameExists(name, id);
	}

	@Override
	public List<CouponEntity> findCouponByCode(String code) {
		return conponDao.findCouponByCode(code);
	}

	@Override
	public CouponEntity checkAvailCode(String code) {
		return conponDao.checkAvailCode(code);
	}

	@Override
	public void subVailable(String code) {
		conponDao.subVailable(code);
	}

	@Override
	public CouponEntity findCouponById(int id) {
		return conponDao.findCouponById(id);
	}

}
