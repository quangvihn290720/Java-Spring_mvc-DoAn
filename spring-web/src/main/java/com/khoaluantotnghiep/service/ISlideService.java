package com.khoaluantotnghiep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.SlideEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface ISlideService {
	public List<SlideEntity> findAllSlide();

	public List<SlideEntity> findAllSlideShow();

	public SlideEntity findSlideById(SlideEntity slide);

	public void addSlide(SlideEntity slide);

	public void deleteSlide(int slide_id);

	public void updateSlide(SlideEntity slide);

	public List<SlideEntity> findTrashSlide();

	public void deltrash(int slide_id,UserEntity loginInfo);

	public void retrash(int slide_id,UserEntity loginInfo);

	public void onOffSlide(int slide_id,UserEntity loginInfo);

	public List<SlideEntity> GetDataSlideTrashPaginate(int start, int totalPage);

	public List<SlideEntity> GetDataSlidePaginate(int start, int totalPage);

	public boolean isNameExists(String name);

	public boolean isNameExists(String name, int id);
}
