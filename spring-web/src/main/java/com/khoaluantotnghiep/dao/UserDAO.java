package com.khoaluantotnghiep.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.mapper.UserMapper;

@Repository
@Transactional
public class UserDAO extends BaseDAO {


	public int addAccount(UserEntity user) {
		String sql = "INSERT INTO user (username,password,user_fullname,user_email,role,role2,user_gender"
				+ ",user_phone,user_img,enabled,reset_password_token,created_by,updated_by,created_at,updated_at) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int insert = jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getUser_fullname(),
				user.getUser_email(), user.getRole(), user.getRole2(), user.getUser_gender(), user.getUser_phone(),
				user.getUser_img(), user.getEnabled(), user.getReset_password_token(), user.getCreated_by(),
				user.getUpdated_by(), user.getCreated_at(), user.getUpdated_at());
		return insert;
	}

	public UserEntity FindAccount(UserEntity user) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from user AS u ");
		sql.append(" WHERE username = ");
		sql.append(" '" + user.getUsername() + "' ");
		sql.append(" AND enabled = 1 ");
		List<UserEntity> result = jdbcTemplate.query(sql.toString(), new UserMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public UserEntity FindAccountById(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from user AS u ");
		sql.append(" WHERE user_id = ");
		sql.append(" '" + id + "' ");
		List<UserEntity> result = jdbcTemplate.query(sql.toString(), new UserMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public UserEntity FindAccountEmail(UserEntity user) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from user AS u ");
		sql.append(" WHERE user_email = ");
		sql.append(" '" + user.getUser_email() + "' ");
		sql.append(" AND enabled = 1 ");
		List<UserEntity> result = jdbcTemplate.query(sql.toString(), new UserMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public UserEntity FindAccEmail(String user_email) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from user AS u ");
		sql.append(" WHERE user_email = ");
		sql.append(" '" + user_email + "' ");
		sql.append(" AND enabled = 1 ");
		List<UserEntity> result = jdbcTemplate.query(sql.toString(), new UserMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void updatePassword(UserEntity user, String newPassword) {
		String uuid = UUID.randomUUID().toString();
		// Remove dashes
		String uuid2 = uuid.replaceAll("-", "");
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		String sql = "UPDATE user SET password = '" + encodedPassword + "' , reset_password_token = '" + uuid2
				+ "'  where user_id = ?";
		jdbcTemplate.update(sql, user.getUser_id());
	}

	public UserEntity findByResetPasswordToken(String token) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * from user AS u ");
		sql.append(" WHERE reset_password_token = ");
		sql.append(" '" + token + "' ");
		sql.append(" AND enabled = 1 ");
		List<UserEntity> result = jdbcTemplate.query(sql.toString(), new UserMapper());
		return result.isEmpty() ? null : result.get(0);
	}

	public void changePasswordById(int id, String newpassword) {
		String sql = "UPDATE user SET password = ? where  user_id = ?";
		jdbcTemplate.update(sql, newpassword, id);
	}

	private StringBuffer SqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM user where enabled != 0 ");
		return sql;
	}

	private String SqlUsersPaginate(int start, int totalPage) {
		StringBuffer sql = SqlString();
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<UserEntity> findAllUser() {
		String sql = SqlString().toString();
		List<UserEntity> list = jdbcTemplate.query(sql, new UserMapper());
		return list;
	}

	public List<UserEntity> GetDataUserPaginate(int start, int totalPage) {
		StringBuffer sqlGetData = SqlString();
		List<UserEntity> listUser = jdbcTemplate.query(sqlGetData.toString(), new UserMapper());
		List<UserEntity> listUsers = new ArrayList<UserEntity>();
		if (listUser.size() > 0) {
			String sql = SqlUsersPaginate(start, totalPage);
			listUsers = jdbcTemplate.query(sql, new UserMapper());
		}
		return listUsers;
	}

	public void setRoleMain(UserEntity user) {
		String sql = "UPDATE user SET role = ?, role2 = ? WHERE user_id = ?";
		jdbcTemplate.update(sql, user.getRole(), user.getRole2(), user.getUser_id());
	}

	public void delete(int id) {
		String sql = "DELETE FROM user WHERE user_id = " + id;
		jdbcTemplate.update(sql);
	}

}