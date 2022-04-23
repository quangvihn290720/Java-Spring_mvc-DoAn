package com.khoaluantotnghiep.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.SlideDAO;
import com.khoaluantotnghiep.entity.SlideEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.ISlideService;

@Service
public class SlideServiceImpl implements ISlideService {
	@Autowired
	SlideDAO slideDAO;

	@Override
	public List<SlideEntity> findAllSlide() {
		return slideDAO.findAllSlide();
	}

	@Override
	public SlideEntity findSlideById(SlideEntity slide) {
		return slideDAO.findSliderById(slide);
	}

	@Override
	public void addSlide(SlideEntity slide) {
		slideDAO.addSlide(slide);
	}

	@Override
	public void deleteSlide(int slide_id) {
		slideDAO.deleteSlide(slide_id);
	}

	@Override
	public void updateSlide(SlideEntity slide) {
		slideDAO.updateSlide(slide);
	}

	@Override
	public List<SlideEntity> findAllSlideShow() {
		return slideDAO.findAllSlideShow();
	}

	@Override
	public void deltrash(int slide_id,UserEntity loginInfo) {
		slideDAO.deltrash(slide_id, loginInfo);
	}

	@Override
	public void retrash(int slide_id,UserEntity loginInfo) {
		slideDAO.retrash(slide_id, loginInfo);
	}

	@Override
	public void onOffSlide(int slide_id,UserEntity loginInfo) {
		slideDAO.onOffSlide(slide_id, loginInfo);
	}

	@Override
	public List<SlideEntity> findTrashSlide() {
		return slideDAO.findTrashSlide();
	}

	@Override
	public List<SlideEntity> GetDataSlideTrashPaginate(int start, int totalPage) {
		return slideDAO.GetDataSlideTrashPaginate(start, totalPage);
	}

	@Override
	public List<SlideEntity> GetDataSlidePaginate(int start, int totalPage) {
		return slideDAO.GetDataSlidePaginate(start, totalPage);
	}
	@Override
	public boolean isNameExists(String name) {
		return slideDAO.isNameExists(name);
	}

	@Override
	public boolean isNameExists(String name, int id) {
		return slideDAO.isNameExists(name, id);
	}
}
