package com.khoaluantotnghiep.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dto.CartDTO;
import com.khoaluantotnghiep.entity.BilldetailEntity;
import com.khoaluantotnghiep.entity.BillsEntity;
import com.khoaluantotnghiep.entity.UserEntity;

@Service
public interface IBillsService {
	public int AddBills(BillsEntity bills, double priceafterpromotion);

	public void AddBillsDetail(HashMap<Integer, CartDTO> carts);

	public List<BillsEntity> getAllBill();

	public BillsEntity findBillById(int id);

	public List<BilldetailEntity> getBillsDetail(int id);

	public void deleteBill(int id);

	public void changeStatus(int id, UserEntity loginInfo);

	public List<BillsEntity> GetDataBillSeachPaginate(int start, int totalPage, String display_name);

	public List<BillsEntity> GetDataBillByEmailPaginate(int start, int totalPage, String email);

	public List<BillsEntity> SearchBill(String display_name);

	public List<BillsEntity> GetDataBillPaginate(int start, int totalPage);

	public List<BillsEntity> findBillsByEmail(String email);

	public List<BillsEntity> GetDataCustomer();

	public List<BillsEntity> GetDataCustomerPaginate(int start, int totalPage);

	public BillsEntity findBillsConfirm(String code);

	public void cancelBill(int id, UserEntity loginInfo);

	public void cancelBillforUser(int id, UserEntity loginInfo);

}
