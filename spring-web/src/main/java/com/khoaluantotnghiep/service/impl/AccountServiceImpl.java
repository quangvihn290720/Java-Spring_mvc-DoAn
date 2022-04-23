package com.khoaluantotnghiep.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.UserDAO;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService {
	@Autowired
	UserDAO userDAO;

	@Override
	public int AddAccount(UserEntity user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
		user.setRole("ROLE_USER");
		user.setRole2(1);
		user.setUser_gender("Nam");
		user.setUser_img("user.png");
		user.setEnabled(1);
		String uuid = UUID.randomUUID().toString();
		String uuid2 = uuid.replaceAll("-", "");
		user.setReset_password_token(uuid2);
		user.setCreated_at(new Date());
		user.setUpdated_at(new Date());
		user.setCreated_by(1);
		user.setUpdated_by(1);
		return userDAO.addAccount(user);
	}

	@Override
	public UserEntity CheckAccount(UserEntity user) {
		String pass = user.getPassword();
		user = userDAO.FindAccount(user);
		if (user != null) {
			if (BCrypt.checkpw(pass, user.getPassword())) {
				user.setPassword(pass);
				return user;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public UserEntity findUserName(UserEntity user) {
		return userDAO.FindAccount(user);
	}

	@Override
	public UserEntity findAccountEmail(UserEntity user) {
		return userDAO.FindAccountEmail(user);
	}

	@Override
	public UserEntity findAccEmail(String user_email) {
		return userDAO.FindAccEmail(user_email);
	}

	@Override
	public UserEntity findByResetPasswordToken(String token) {
		return userDAO.findByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(UserEntity user, String newPassword) {
		userDAO.updatePassword(user, newPassword);
	}

	@Override
	public boolean comparePasswords(HttpSession session, String password) {
		UserEntity user = (UserEntity) session.getAttribute("LoginInfo");
//		if (BCrypt.checkpw(password, user.getPassword())) {
//			return true;
//		}
//		return false;
		return password.equals(user.getPassword());
	}

	@Override
	public void changePassword(HttpSession session, String newPassword) {
		UserEntity user = (UserEntity) session.getAttribute("LoginInfo");
		String pass = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
		userDAO.changePasswordById(user.getUser_id(), pass);
	}

	@Override
	public List<UserEntity> findAllUser() {
		return userDAO.findAllUser();
	}

	@Override
	public List<UserEntity> GetDataUserPaginate(int start, int totalPage) {
		return userDAO.GetDataUserPaginate(start, totalPage);
	}

	@Override
	public UserEntity findAccountById(int id) {
		return userDAO.FindAccountById(id);
	}

	@Override
	public Map<Integer, String> mapUser() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		List<UserEntity> list = userDAO.findAllUser();
		for (UserEntity ct : list) {
			map.put(ct.getUser_id(), ct.getUser_fullname());
		}
		return map;
	}

	@Override
	public void setRoleMain(UserEntity user) {
		userDAO.setRoleMain(user);
	}

	@Override
	public void delete(int id) {
		userDAO.delete(id);
	}
}
