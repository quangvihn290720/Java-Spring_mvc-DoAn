package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.OptionsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.OptionsMapper;

@Transactional
@Repository
public class OptionsDAO extends BaseDAO {
	public List<OptionsEntity> findAllOption() {
		String sql = "SELECT * FROM options where status != 0 ";
		List<OptionsEntity> list = jdbcTemplate.query(sql, new OptionsMapper());
		return list;
	}

	public List<OptionsEntity> findAllOptionShow() {
		String sql = "SELECT * FROM options where status = 1 ";
		List<OptionsEntity> list = jdbcTemplate.query(sql, new OptionsMapper());
		return list;
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM options where status != 0 ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM options where status = 0 ");
		return sql;
	}

	private String SqlOptionPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlOptionTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<OptionsEntity> GetDataOptionPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<OptionsEntity> listOption = jdbcTemplate.query(sqlGetData.toString(), new OptionsMapper());
		List<OptionsEntity> listOptions = new ArrayList<OptionsEntity>();
		if (listOption.size() > 0) {
			String sql = SqlOptionPaginate(start, totalPage);
			listOptions = jdbcTemplate.query(sql, new OptionsMapper());
		}
		return listOptions;
	}

	public List<OptionsEntity> GetDataOptionTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<OptionsEntity> listOptionTrash = jdbcTemplate.query(sqlGetData.toString(), new OptionsMapper());
		List<OptionsEntity> listOptionTrashs = new ArrayList<OptionsEntity>();
		if (listOptionTrash.size() > 0) {
			String sql = SqlOptionTrashsPaginate(start, totalPage);
			listOptionTrashs = jdbcTemplate.query(sql, new OptionsMapper());
		}
		return listOptionTrashs;
	}

	public List<OptionsEntity> findAllTrashOption() {
		List<OptionsEntity> list = new ArrayList<OptionsEntity>();
		String sql = "SELECT * FROM options where status = 0 ";
		list = jdbcTemplate.query(sql, new OptionsMapper());
		return list;
	}

	public OptionsEntity findOptionById(OptionsEntity option) {
		String sql = "SELECT * FROM options WHERE options_id = " + option.getOptions_id();
		List<OptionsEntity> result = jdbcTemplate.query(sql, new OptionsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public OptionsEntity findOptionId(int options_id) {
		String sql = "SELECT * FROM options WHERE options_id = " + options_id;
		List<OptionsEntity> result = jdbcTemplate.query(sql, new OptionsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public OptionsEntity findOptionByName(OptionsEntity option) {
		String sql = "SELECT * FROM options WHERE optionname = '" + option.getOptionname() + "' AND optiongroup_id = "
				+ option.getOptiongroup_id();
		List<OptionsEntity> result = jdbcTemplate.query(sql, new OptionsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addOption(OptionsEntity option) {
		String sql = "INSERT INTO `options`(`optiongroup_id`, `optionname`, `metadesc`, `metakey`, `status`,"
				+ " `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, option.getOptiongroup_id(), option.getOptionname(), option.getMetadesc(),
				option.getMetakey(), option.getStatus(), option.getCreated_by(), option.getUpdated_by(),
				option.getCreated_at(), option.getUpdated_at());
	}

	public void updateOption(OptionsEntity option) {
		String sql = "UPDATE options SET `optiongroup_id` = ?, `optionname` = ?, `metadesc` = ?, `metakey` = ?, `status` = ?, "
				+ "`updated_by` = ?, `updated_at` = ? WHERE options_id = ? ";
		jdbcTemplate.update(sql, option.getOptiongroup_id(), option.getOptionname(), option.getMetadesc(),
				option.getMetakey(), option.getStatus(), option.getUpdated_by(), option.getUpdated_at(),
				option.getOptions_id());
	}

	public void deleteOption(int options_id) {
		String sql = "DELETE FROM options WHERE options_id  = " + options_id;
		jdbcTemplate.update(sql);
	}

	public void deltrash(int options_id, UserEntity loginInfo) {
		String sql = "UPDATE options SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE options_id  = " + options_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int options_id, UserEntity loginInfo) {
		String sql = "UPDATE options SET status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE options_id  = " + options_id;
		jdbcTemplate.update(sql);
	}

	public void onOff(int options_id, UserEntity loginInfo) {
		String sql = "UPDATE options SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE options_id  = " + options_id;
		jdbcTemplate.update(sql);
	}

	public OptionsEntity findOtherOptionByName(OptionsEntity option) {
		String sql = "SELECT * FROM options WHERE optionname = '" + option.getOptionname() + "' AND optiongroup_id = "
				+ option.getOptiongroup_id() + " and options_id <>" + option.getOptions_id();
		List<OptionsEntity> result = jdbcTemplate.query(sql, new OptionsMapper());
		return result.isEmpty() ? null : result.get(0);
	}
}
