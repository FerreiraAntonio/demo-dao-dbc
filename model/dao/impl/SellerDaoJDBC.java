package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
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

	public SellerDaoJDBC() {
	}

	@Override
	public void insert(Seller obj) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		Seller seller = new Seller();
		try {
			st = conn.prepareStatement("INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId ) "
					+ "VALUES " + "(?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmnail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setInt(5, obj.getDepartment().getId());
			st.setDouble(4, obj.getSalary());
			// Departments dep=new Departments();

			int rows = st.executeUpdate();
			if (rows > 0) {

				rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					System.out.println("Foram cirados " + rows + " linhas");
				}
			} else {

				throw new DbException("Erro inesperado- nao add rows");
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}

	}

	@Override
	public void update(Seller obj) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ?" + " WHERE Id = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmnail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getSalary());
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}
	}

	@Override
	public void deleteByid(Integer id) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("DELETE FROM seller "+
					"WHERE Id = ?");

			st.setInt(1, id);

		int del=st.executeUpdate();
			if(del>0) {
				System.out.println("Seu vendedor foi apagado com sussesso");
			}
			else {System.out.println("A função delete nao foi executada");}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}
	}

	@Override
	public Seller findById(Integer id) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		Seller seller = new Seller();
		try {
			st = conn.prepareStatement(
					" SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departments dep = new Departments();
				dep = instanciateDepartment(rs);
				seller = instanciateSeller(rs, dep);
				return seller;
			}
			return null;
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}

	}

	@Override
	public List<Seller> findAll() {

		java.sql.PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "ORDER BY Name");

			rs = st.executeQuery();
			Map<Integer, Departments> map = new HashMap<>();
			List<Seller> list = new ArrayList<>();
			while (rs.next()) {
				Departments dep = new Departments();
				dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);

				}
				Seller seller = new Seller();
				seller = instanciateSeller(rs, dep);
				list.add(seller);

			}
			return list;
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}
	}

	public List<Seller> DepartmentFindById(Departments department) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE DepartmentId = ? " + "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			Map<Integer, Departments> map = new HashMap<>();
			List<Seller> list = new ArrayList<>();
			while (rs.next()) {
				Departments dep = new Departments();
				dep = map.get(rs.getInt("DepartmentId"));

				if (dep == null) {
					dep = instanciateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);

				}
				Seller seller = new Seller();
				seller = instanciateSeller(rs, dep);
				list.add(seller);

			}
			return list;
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}

	}

	public Departments instanciateDepartment(ResultSet rs) throws SQLException {
		Departments dep = new Departments();
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
