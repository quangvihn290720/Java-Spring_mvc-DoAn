package com.khoaluantotnghiep.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IAccountService {
	public int AddAccount(UserEntity user);

	public UserEntity CheckAccount(UserEntity user);

	public UserEntity findUserName(UserEntity user);

	public UserEntity findAccountEmail(UserEntity user);

	public UserEntity findAccEmail(String user_email);

	public UserEntity findByResetPasswordToken(String token);

	public void updatePassword(UserEntity user, String newPassword);

	public boolean comparePasswords(HttpSession session, String password);

	public void changePassword(HttpSession session, String newPassword);

	public List<UserEntity> findAllUser();

	public List<UserEntity> GetDataUserPaginate(int start, int totalPage);

	public UserEntity findAccountById(int id);

	public void setRoleMain(UserEntity user);

	public Map<Integer, String> mapUser();

	public void delete(int id);
}