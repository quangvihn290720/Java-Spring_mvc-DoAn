package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.OptiongroupsEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.OptiongroupsMapper;

@Transactional
@Repository
public class OptiongroupsDAO extends BaseDAO {

	public List<OptiongroupsEntity> findAllOptionGroup() {
		String sql = "SELECT * FROM optiongroups";
		List<OptiongroupsEntity> list = jdbcTemplate.query(sql, new OptiongroupsMapper());
		return list;
	}

	public List<OptiongroupsEntity> findAllOptionGroupShow() {
		String sql = "SELECT * FROM optiongroups WHERE status = 1 ";
		List<OptiongroupsEntity> list = jdbcTemplate.query(sql, new OptiongroupsMapper());
		return list;
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM optiongroups where status != 0 ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM optiongroups where status = 0 ");
		return sql;
	}

	private String SqlOptionGroupPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlOptionGroupTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<OptiongroupsEntity> GetDataOptionGroupPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<OptiongroupsEntity> listOptionGroup = jdbcTemplate.query(sqlGetData.toString(), new OptiongroupsMapper());
		List<OptiongroupsEntity> listOptionGroups = new ArrayList<OptiongroupsEntity>();
		if (listOptionGroup.size() > 0) {
			String sql = SqlOptionGroupPaginate(start, totalPage);
			listOptionGroups = jdbcTemplate.query(sql, new OptiongroupsMapper());
		}
		return listOptionGroups;
	}

	public List<OptiongroupsEntity> GetDataOptionGroupTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<OptiongroupsEntity> listOptionGroupTrash = jdbcTemplate.query(sqlGetData.toString(),
				new OptiongroupsMapper());
		List<OptiongroupsEntity> listOptionGroupTrashs = new ArrayList<OptiongroupsEntity>();
		if (listOptionGroupTrash.size() > 0) {
			String sql = SqlOptionGroupTrashsPaginate(start, totalPage);
			listOptionGroupTrashs = jdbcTemplate.query(sql, new OptiongroupsMapper());
		}
		return listOptionGroupTrashs;
	}

	public List<OptiongroupsEntity> findAllTrashOptionGroup() {
		List<OptiongroupsEntity> list = new ArrayList<OptiongroupsEntity>();
		String sql = "SELECT * FROM optiongroups where status = 0 ";
		list = jdbcTemplate.query(sql, new OptiongroupsMapper());
		return list;
	}

	public OptiongroupsEntity findOptionGroupById(OptiongroupsEntity optiongroup) {
		String sql = "SELECT * FROM optiongroups WHERE optiongroups_id = " + optiongroup.getOptiongroups_id();
		List<OptiongroupsEntity> result = jdbcTemplate.query(sql, new OptiongroupsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public OptiongroupsEntity findOptionGroupId(int optiongroups_id) {
		String sql = "SELECT * FROM optiongroups WHERE optiongroups_id = " + optiongroups_id;
		List<OptiongroupsEntity> result = jdbcTemplate.query(sql, new OptiongroupsMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void addOptionGroup(OptiongroupsEntity optiongroup) {
		String sql = "INSERT INTO `optiongroups`(`optiongroupname`, `metadesc`, `metakey`, `status`,"
				+ " `created_by`, `updated_by`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, optiongroup.getOptiongroupname(), optiongroup.getMetadesc(), optiongroup.getMetakey(),
				optiongroup.getStatus(), optiongroup.getCreated_by(), optiongroup.getUpdated_by(),
				optiongroup.getCreated_at(), optiongroup.getUpdated_at());
	}

	public void updateOptionGroup(OptiongroupsEntity optiongroup) {
		String sql = "UPDATE optiongroups SET `optiongroupname` = ?, `metadesc` = ?, `metakey`  = ?, `status`  = ?,"
				+ " `updated_by`  = ?, `updated_at` = ? WHERE optiongroups_id = ?";
		jdbcTemplate.update(sql, optiongroup.getOptiongroupname(), optiongroup.getMetadesc(), optiongroup.getMetakey(),
				optiongroup.getStatus(), optiongroup.getUpdated_by(), optiongroup.getUpdated_at(),
				optiongroup.getOptiongroups_id());
	}

	public void deleteOptionGroup(int optiongroups_id) {
		String sql = "DELETE FROM optiongroups WHERE optiongroups_id  = " + optiongroups_id;
		jdbcTemplate.update(sql);
	}

	public void deltrash(int optiongroups_id, UserEntity loginInfo) {
		String sql = "UPDATE optiongroups SET status = 0,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE optiongroups_id   = " + optiongroups_id;
		jdbcTemplate.update(sql);
	}

	public void retrash(int optiongroups_id, UserEntity loginInfo) {
		String sql = "UPDATE optiongroups SET status = 2,updated_by = " + loginInfo.getUser_id()
				+ ", updated_at = NOW() WHERE optiongroups_id   = " + optiongroups_id;
		jdbcTemplate.update(sql);
	}

	public void onOff(int optiongroups_id, UserEntity loginInfo) {
		String sql = "UPDATE optiongroups SET status = case WHEN status =1 then 2 when status=2 then 1 end,updated_by = "
				+ loginInfo.getUser_id() + ", updated_at = NOW() WHERE optiongroups_id   = " + optiongroups_id;
		jdbcTemplate.update(sql);
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM optiongroups WHERE optiongroupname = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM optiongroups WHERE optiongroupname = ? and optiongroups_id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}
}
