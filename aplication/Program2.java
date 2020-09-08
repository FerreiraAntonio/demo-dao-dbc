package aplication;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactury;
import model.dao.DepartmentDao;
import model.entities.Departments;

public class Program2 {

	public static void main(String[] args) {
		
		
		DepartmentDao dao= new DaoFactury().createDepartamentDao();
		
		dao.findById(2); 
		Departments dep =dao.findById(2); 
		System.out.println(dep);
		
		
		List<Departments>list= dao.findAll();
		
		for(Departments de: list) {
			System.out.println(de);
		}
		
		
		Departments dept=new Departments(5, "Games");
		//dao.insert(dept);
		
		
		dao.findById(5);
		dept.setName("Toys");
		dao.update(dept);
		
		dao.deleteByid(5);
		
	}

}
