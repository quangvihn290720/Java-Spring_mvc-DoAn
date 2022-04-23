package com.khoaluantotnghiep.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.khoaluantotnghiep.entity.OptionsEntity;

public class OptionsMapper implements RowMapper<OptionsEntity> {

	@Override
	public OptionsEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		OptionsEntity option = new OptionsEntity();
		option.setOptions_id(rs.getInt("options_id"));
		option.setOptiongroup_id(rs.getInt("optiongroup_id"));
		option.setOptionname(rs.getString("optionname"));
		option.setMetakey(rs.getString("metakey"));
		option.setMetadesc(rs.getString("metadesc"));
		option.setStatus(rs.getInt("status"));
		option.setCreated_by(rs.getInt("created_by"));
		option.setUpdated_by(rs.getInt("updated_by"));
		option.setCreated_at(rs.getDate("created_at"));
		option.setUpdated_at(rs.getDate("updated_at"));
		return option;
	}

}
