package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.ServiceEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.ServiceMapper;

@Repository
@Transactional
public class ServiceDAO extends BaseDAO {
	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM service where status != 0 ORDER BY status ASC ");
		return sql;
	}

	private StringBuffer SqlTrash() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM service where status = 0 ");
		return sql;
	}

	private String SqlServicePaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	private String SqlServiceTrashsPaginate(int start, int totalPage) {
		StringBuffer sql = SqlTrash();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ServiceEntity> GetDataServicePaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<ServiceEntity> listService = jdbcTemplate.query(sqlGetData.toString(), new ServiceMapper());
		List<ServiceEntity> listServices = new ArrayList<ServiceEntity>();
		if (listService.size() > 0) {
			String sql = SqlServicePaginate(start, totalPage);
			listServices = jdbcTemplate.query(sql, new ServiceMapper());
		}
		return listServices;
	}

	public List<ServiceEntity> GetDataServiceTrashPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlTrash();
		List<ServiceEntity> listServiceTrash = jdbcTemplate.query(sqlGetData.toString(), new ServiceMapper());
		List<ServiceEntity> listServiceTrashs = new ArrayList<ServiceEntity>();
		if (listServiceTrash.size() > 0) {
			String sql = SqlServiceTrashsPaginate(start, totalPage);
			listServiceTrashs = jdbcTemplate.query(sql, new ServiceMapper());
		}
		return listServiceTrashs;
	}

	public List<ServiceEntity> findAllService() {
		String sql = "SELECT * FROM service where status != 0";
		List<ServiceEntity> list = jdbcTemplate.query(sql, new ServiceMapper());
		return list;
	}

	public List<ServiceEntity> findAllServiceShow() {
		String sql = "SELECT * FROM service where status = 1 ";
		List<ServiceEntity> list = jdbcTemplate.query(sql, new ServiceMapper());
		return list;
	}

	public List<ServiceEntity> findTrashService() {
		String sql = SqlTrash().toString();
		List<ServiceEntity> list = jdbcTemplate.query(sql, new ServiceMapper());
		return list;
	}

	public ServiceEntity findServiceById(int id) {
		String sql = "SELECT * FROM service where id = " + id;
		List<ServiceEntity> result = jdbcTemplate.query(sql, new ServiceMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void onOff(int id,UserEntity loginInfo) {
		String sql = "UPDATE service SET status = case when  status =1 then 2 when  status =2 then 1 end,updated_by ="
				+ loginInfo.getUser_id() + ", updated_at = NOW() where  id =" + id;
		jdbcTemplate.update(sql);
	}

	public void delTrash(int id,UserEntity loginInfo) {
		String sql = "UPDATE service SET status = 0,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW()  where id =" + id;
		jdbcTemplate.update(sql);
	}

	public void reTrash(int id,UserEntity loginInfo) {
		String sql = "UPDATE service SET status = 2,updated_by =" + loginInfo.getUser_id()
				+ ", updated_at = NOW()  where  id =" + id;
		jdbcTemplate.update(sql);
	}

	public void update(ServiceEntity service) {
		String sql = "";
		if (service.getImg() != null && !service.getImg().isEmpty()) {
			sql = "UPDATE `service` SET `name` = ?, `img` = ?, `metadesc` = ?, `metakey` = ?, `status` = ?,"
					+ "`updated_at` = ?, `updated_by` = ? WHERE id = ?";
			jdbcTemplate.update(sql, service.getName(), service.getImg(), service.getMetadesc(), service.getMetakey(),
					service.getStatus(), service.getUpdated_at(), service.getUpdated_by(), service.getId());
		} else {
			sql = "UPDATE `service` SET `name` = ?, `metadesc` = ?, `metakey` = ?, `status` = ?,"
					+ "`updated_at` = ?, `updated_by` = ? WHERE id = ?";
			jdbcTemplate.update(sql, service.getName(), service.getMetadesc(), service.getMetakey(),
					service.getStatus(), service.getUpdated_at(), service.getUpdated_by(), service.getId());
		}
	}

	public void delete(int id) {
		String sql = "DELETE FROM service WHERE id = " + id;
		jdbcTemplate.update(sql);
	}

	public void add(ServiceEntity service) {
		String sql = "INSERT INTO `service`(`name`, `img`, `metadesc`, `metakey`, `status`,"
				+ "`created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, service.getName(), service.getImg(), service.getMetadesc(), service.getMetakey(),
				service.getStatus(), service.getCreated_at(), service.getCreated_by(), service.getUpdated_at(),
				service.getUpdated_by());
	}

	public boolean isNameExists(String name) {
		int count = 0;
		String sql = "SELECT count(*) FROM service WHERE name = ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name }, Integer.class);

		return count > 0;
	}

	public boolean isNameExists(String name, int id) {
		int count = 0;
		String sql = "SELECT count(*) FROM service WHERE name = ? and id <> ?";
		count = jdbcTemplate.queryForObject(sql, new Object[] { name, id }, Integer.class);

		return count > 0;
	}
}
