package model.dao;

import model.dao.impl.SellerDaoJDBC;
import db.DB;
import model.dao.SellerDao;

public class DaoFactury {

	public static SellerDao createSellerDao() {
		return  new SellerDaoJDBC(DB.getConnection());
	}

}
