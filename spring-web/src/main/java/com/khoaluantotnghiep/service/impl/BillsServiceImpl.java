package com.khoaluantotnghiep.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khoaluantotnghiep.dao.BillsDAO;
import com.khoaluantotnghiep.dao.ProductDAO;
import com.khoaluantotnghiep.dto.CartDTO;
import com.khoaluantotnghiep.entity.BilldetailEntity;
import com.khoaluantotnghiep.entity.BillsEntity;
import com.khoaluantotnghiep.entity.ProductEntity;
import com.khoaluantotnghiep.entity.UserEntity;
import com.khoaluantotnghiep.service.IBillsService;

@Service
public class BillsServiceImpl implements IBillsService {
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String strDate = formatter.format(date);
	@Autowired
	private BillsDAO billsDAO;
	@Autowired
	private ProductDAO productDAO;

	@Override
	public int AddBills(BillsEntity bills, double priceafterpromotion) {
		bills.setCreated_at(strDate);
		bills.setUpdated_at(strDate);
		return billsDAO.AddBills(bills, priceafterpromotion);
	}

	@Override
	public void AddBillsDetail(HashMap<Integer, CartDTO> carts) {
		int idBills = billsDAO.GetIdLastBills();

		for (Map.Entry<Integer, CartDTO> itemCart : carts.entrySet()) {
			BilldetailEntity billdetail = new BilldetailEntity();
			billdetail.setBills_id(idBills);
			billdetail.setProduct_id(itemCart.getValue().getProductEntity().getProduct_id());
			billdetail.setQuanty(itemCart.getValue().getQuanty());
			billdetail.setTotal(itemCart.getValue().getTotalprice());
			billsDAO.AddBillsDetail(billdetail);
		}
	}

	@Override
	public List<BillsEntity> getAllBill() {
		List<BillsEntity> list = billsDAO.getAllBill();
		return list;
	}

	@Override
	public BillsEntity findBillById(int id) {
		return billsDAO.findBillById(id);
	}

	@Override
	public List<BilldetailEntity> getBillsDetail(int id) {
		List<BilldetailEntity> list = billsDAO.getBillsDetail(id);
		for (BilldetailEntity it : list) {
			ProductEntity product = productDAO.findProductById(it.getProduct_id());
			it.setProductinfo(product);
		}
		return list;
	}

	@Override
	public void deleteBill(int id) {
		billsDAO.deleteBill(id);
	}

	@Override
	public void changeStatus(int id, UserEntity loginInfo) {
		billsDAO.changeStatus(id,  loginInfo);
	}

	@Override
	public List<BillsEntity> GetDataBillSeachPaginate(int start, int totalPage, String display_name) {
		return billsDAO.GetDataBillSeachPaginate(start, totalPage, display_name);
	}

	@Override
	public List<BillsEntity> SearchBill(String display_name) {
		return billsDAO.SearchBill(display_name);
	}

	@Override
	public List<BillsEntity> GetDataBillPaginate(int start, int totalPage) {
		return billsDAO.GetDataBillPaginate(start, totalPage);
	}

	@Override
	public List<BillsEntity> findBillsByEmail(String email) {
		return billsDAO.findBillsByEmail(email);
	}

	@Override
	public List<BillsEntity> GetDataBillByEmailPaginate(int start, int totalPage, String email) {
		return billsDAO.GetDataBillByEmailPaginate(start, totalPage, email);
	}

	@Override
	public List<BillsEntity> GetDataCustomer() {
		return billsDAO.GetDataCustomer();
	}

	@Override
	public List<BillsEntity> GetDataCustomerPaginate(int start, int totalPage) {
		return billsDAO.GetDataCustomerPaginate(start, totalPage);
	}

	@Override
	public BillsEntity findBillsConfirm(String code) {
		return billsDAO.findBillsConfirm(code);
	}
	@Override
	public void cancelBill(int id,UserEntity loginInfo) {
		billsDAO.cancelBill(id, loginInfo);
		
	}

	@Override
	public void cancelBillforUser(int id, UserEntity loginInfo) {
		billsDAO.cancelBillforUser(id, loginInfo);
	}

}
