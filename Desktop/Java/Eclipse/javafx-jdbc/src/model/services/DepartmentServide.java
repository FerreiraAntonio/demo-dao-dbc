package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentServide {
	
	public List<Department> findAll(){
		List<Department>list=new ArrayList<>();
		list.add(new Department(1, "Book"));
		list.add(new Department(2, "TV"));
		list.add(new Department(3, "forniture"));
		list.add(new Department(4, "Music"));
		return list;
	};

}
