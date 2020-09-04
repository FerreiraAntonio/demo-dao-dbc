package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departments;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	public SellerDaoJDBC() {}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByid(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		java.sql.PreparedStatement st = null;
		ResultSet rs =null;
		Seller seller=new Seller();
		try {
			st=conn.prepareStatement(" SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE seller.Id = ? ");
			st.setInt(1,id);
			rs=st.executeQuery();
				if(rs.next()) {
					Departments dep= new Departments();
					dep =instanciateDepartment(rs);
					seller =instanciateSeller(rs, dep);
					return seller;
				}
				return null;
		}
		catch(SQLException e) {
			
			throw new DbException(e.getMessage());
		}
		finally {
			
			DB.closeRs(rs);
			DB.closeSt(st);
				
			
			
		}
		
	}

	@Override
	public List<Seller> findAll() {
		
		java.sql.PreparedStatement st = null;
		ResultSet rs =null;
			
		try {
			st=conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"ORDER BY Name");
			
			rs=st.executeQuery();
			Map <Integer,Departments> map=new HashMap<>();
			List <Seller> list= new ArrayList<>();
				while(rs.next()) {
					Departments dep=new Departments();
				dep=map.get(rs.getInt("DepartmentId"));	
				
				if(dep==null) {
					dep=instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				 
				}
				Seller seller=new Seller();
				seller=instanciateSeller(rs, dep);	
				 list.add(seller);
					
				}
				return list;
		}
		catch(SQLException e) {
			
			throw new DbException(e.getMessage());
		}
		finally {
			
			DB.closeRs(rs);
			DB.closeSt(st);
				
			
			
		}
	}

	
	public List<Seller> DepartmentFindById(Departments department) {
		java.sql.PreparedStatement st = null;
		ResultSet rs =null;
			
		try {
			st=conn.prepareStatement("SELECT seller.*,department.Name as DepName "+
					"FROM seller INNER JOIN department "+
					"ON seller.DepartmentId = department.Id "+
					"WHERE DepartmentId = ? "+
					"ORDER BY Name");
			st.setInt(1,department.getId() );
			rs=st.executeQuery();
			Map <Integer,Departments> map=new HashMap<>();
			List <Seller> list= new ArrayList<>();
				while(rs.next()) {
					Departments dep=new Departments();
				dep=map.get(rs.getInt("DepartmentId"));	
				
				if(dep==null) {
					dep=instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				 
				}
				Seller seller=new Seller();
				seller=instanciateSeller(rs, dep);	
				 list.add(seller);
					
				}
				return list;
		}
		catch(SQLException e) {
			
			throw new DbException(e.getMessage());
		}
		finally {
			
			DB.closeRs(rs);
			DB.closeSt(st);
				
			
			
		}
		
	}
	
	
	public Departments instanciateDepartment(ResultSet rs) throws SQLException {
		Departments dep= new Departments();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
		
	}
	
	
	public Seller instanciateSeller(ResultSet rs, Departments dep) throws SQLException {
			
		Seller seller = new Seller();
		seller.setEmnail(rs.getString("Email"));
		seller.setName(rs.getString("Name"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setSalary(rs.getDouble("BaseSalary"));
		seller.setId(rs.getInt("Id"));
		seller.setDepartment(dep);
		return seller;
		
	}

}
