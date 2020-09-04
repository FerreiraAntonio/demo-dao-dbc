package aplication;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactury;
import model.dao.SellerDao;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Departments;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Departments obj =new Departments(1, "Books");
		int i=0;
		int i2=0;
		SellerDao dao = new DaoFactury().createSellerDao();
		
		
		Seller seller= dao.findById(3);
		System.out.println("\n====================================findById==========================================================");		
	
		System.out.println(seller);
System.out.println("\n====================================DepartmentFindById==========================================================");

		Departments obj2= new Departments(2,null);
		List<Seller>seller2=dao.DepartmentFindById(obj2);
		for (Seller s: seller2) {
			i+=1;
			System.out.println(s);
		}
		System.out.println("Quantidade de arq="+i);
System.out.println("\n====================================DepartmentFindById==========================================================");
		
	seller2=dao.findAll();
	
	for (Seller s: seller2) {
		i2+=1;
		System.out.println(s);
		}	
	System.out.println("Quantidade de arq="+i2);

	}

}
