package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Departments;


public class DepartmentDaoJDBC implements DepartmentDao{
	
	public Connection conn;
	public DepartmentDaoJDBC(Connection conn) {
		this.conn=conn;
	}

	public DepartmentDaoJDBC() {
	}

	@Override
	public void insert(Departments obj) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		Departments Departments = new Departments();
		try {
			st = conn.prepareStatement("INSERT INTO department " + "(Id,Name ) "
					+ "VALUES " + "(?, ?) ", Statement.RETURN_GENERATED_KEYS);

			st.setInt(1,obj.getId());
			st.setString(2, obj.getName());
			
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
	public void update(Departments obj) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("UPDATE department "
					+ "SET Id = ?, Name = ?" + " WHERE Id = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getName());
			st.setInt(3, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM department "+
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
	public List<Departments> findAll() {

		java.sql.PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT * FROM department" + " ORDER BY Name");

			rs = st.executeQuery();
			
			List<Departments> list = new ArrayList<>();
			while (rs.next()) {
				Departments dep = new Departments();
				
				dep=instanciateDepartments(rs);
				list.add(dep);
			}
				return list;
				
			
			
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}
	}

	@Override
	public Departments findById(Integer id) {
		java.sql.PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * FROM department "
							 + "WHERE Id = ? " );
			st.setInt(1, id);
			
			rs = st.executeQuery();
			if(rs.next()) {
			Departments dep= new Departments();
			dep=instanciateDepartments(rs);
			
			return dep;
			}
			return null;
		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeRs(rs);
			DB.closeSt(st);

		}

	}

	public Departments instanciateDepartments(ResultSet rs) throws SQLException {
		Departments dep = new Departments();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;

	



	}
	

}
