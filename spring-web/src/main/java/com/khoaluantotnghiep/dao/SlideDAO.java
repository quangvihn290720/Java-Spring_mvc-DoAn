package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.SlideEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.SlideMapper;

@Repository
@Transactional
public class SlideDAO extends BaseDAO {

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM slide where slide_status != 0  order by slide_status asc ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM slide where slide_status = 0 ");
		return sql;
	}

	private String SqlSlidePaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlSlideTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<SlideEntity> GetDataSlidePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<SlideEntity> listSlide = jdbcTemplate.query(sqlGetData.toString(), new SlideMapper());
		List<SlideEntity> listSlides = new ArrayList<SlideEntity>();
		if (listSlide.size() > 0) {
			String sql = SqlSlidePaginate(start, totalPage);
			listSlides = jdbcTemplate.query(sql, new SlideMapper());
		}
		return listSlides;
	}

	public List<SlideEntity> GetDataSlideTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<SlideEntity> listSlideTrash = jdbcTemplate.query(sqlGetData.toString(), new SlideMapper());
		List<SlideEntity> listSlideTrashs = new ArrayList<SlideEntity>();
		if (listSlideTrash.size() > 0) {
			String sql = SqlSlideTrashsPaginate(start, totalPage);
			listSlideTrashs = jdbcTemplate.query(sql, new SlideMapper());
		}
		return listSlideTrashs;
	}

	public List<SlideEntity> findAllSlide() {
		String sql = "SELECT * FROM slide where slide_status != 0 order by slide_status asc ";
		List<SlideEntity> list = jdbcTemplate.query(sql, new SlideMapper());
		return list;
	}

	public List<SlideEntity> findAllSlideShow() {
		String sql = "SELECT * FROM slide where slide_status = 1";
		List<SlideEntity> list = jdbcTemplate.query(sql, new SlideMapper());
		return list;
	}

	public SlideEntity findSliderById(SlideEntity slide) {
		String sql = "SELECT * FROM slide where slide_id = " + slide.getSlide_id();
		List<SlideEntity> result = jdbcTemplate.query(sql, new SlideMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addSlide(SlideEntity slide) {
		String sql = "INSERT INTO slide (slide_caption,slide_img,slide_status,created_by,updated_by,created_at,updated_at) VALUES(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, slide.getSlide_caption(), slide.getSlide_img(), slide.getSlide_status(),
				slide.getCreated_by(), slide.getUpdated_by(), slide.getCreated_at(), slide.getUpdated_at());
	}

	public void deleteSlide(int slide_id) {
		String sql = "DELETE FROM slide WHERE slide_id = " + slide_id;
		jdbcTemplate.update(sql);
	}

	public void updateSlide(SlideEntity slide) {
		String sql = "";
		if (slide.getSlide_img() != null && !slide.getSlide_img().isEmpty()) {
			sql = "UPDATE slide SET slide_caption = ?, slide_img = ?, slide_status = ?, updated_by = ?, updated_at = ? WHERE slide_id = ?";
			jdbcTemplate.update(sql, slide.getSlide_caption(), slide.getSlide_img(), slide.getSlide_status(),
					slide.getUpdated_by(), slide.getUpdated_at(), slide.getSlide_id());
		} else {
			sql = "UPDATE slide SET slide_caption = ?,slide_status = ?, updated_by = ?, updated_at = ? WHERE slide_id = ?";
			jdbcTemplate.update(sql, slide.getSlide_caption(), slide.getSlide_status(), slide.getUpdated_by(),
					slide.getUpdated_at(), slide.getSlide_id());
		}
	}

	public List<SlideEntity> findTrashSlide() {
		String sql = "SELECT * FROM slide where slide_status = 0";
		List<SlideEntity> list = jdbcTemplate.query(sql, new SlideMapper());
		return list;
	}

	public void deltrash(int slide_id,UserEntity loginInfo) {
		String sql = "UPDATE slide SET slide_status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE slide_id = " + slide_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int slide_id,UserEntity loginInfo) {
		String sql = "UPDATE slide SET slide_status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE slide_id = " + slide_id;
		jdbcTemplate.update(sql);
	}

	public void onOffSlide(int slide_id,UserEntity loginInfo) {
		String sql = "UPDATE slide SET slide_status = case WHEN slide_status =1 then 2 when slide_status=2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE slide_id = " + slide_id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM slide WHERE slide_caption = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM slide WHERE slide_caption = ? and slide_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}
}
