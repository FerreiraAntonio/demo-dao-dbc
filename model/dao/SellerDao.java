package model.dao;

import java.util.List;

import model.entities.Departments;
import model.entities.Seller;

public interface SellerDao {
	
	
	void insert (Seller obj);
	void update(Seller obj);
	void deleteByid(Integer id); 
	
	Seller findById(Integer id);
	List<Seller>DepartmentFindById(Departments department);
	
	List<Seller> findAll();

}
