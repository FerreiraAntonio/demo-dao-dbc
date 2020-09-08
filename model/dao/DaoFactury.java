package model.dao;

import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import db.DB;
import model.dao.SellerDao;
import model.dao.DepartmentDao;

public class DaoFactury {

	public static SellerDao createSellerDao() {
		return  new SellerDaoJDBC(DB.getConnection());
	}

	public static DepartmentDao createDepartamentDao() {
		return  new DepartmentDaoJDBC(DB.getConnection());
	}
}
