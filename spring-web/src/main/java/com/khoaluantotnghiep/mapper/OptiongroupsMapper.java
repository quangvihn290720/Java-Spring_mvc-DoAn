package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.OptiongroupsEntity;

public class OptiongroupsMapper implements RowMapper<OptiongroupsEntity> {

	@Override
	public OptiongroupsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		OptiongroupsEntity optiongroups = new OptiongroupsEntity();
		optiongroups.setOptiongroups_id(rs.getInt("optiongroups_id"));
		optiongroups.setOptiongroupname(rs.getString("optiongroupname"));
		optiongroups.setMetakey(rs.getString("metakey"));
		optiongroups.setMetadesc(rs.getString("metadesc"));
		optiongroups.setStatus(rs.getInt("status"));
		optiongroups.setCreated_by(rs.getInt("created_by"));
		optiongroups.setUpdated_by(rs.getInt("updated_by"));
		optiongroups.setCreated_at(rs.getDate("created_at"));
		optiongroups.setUpdated_at(rs.getDate("updated_at"));
		return optiongroups;
	}

}
