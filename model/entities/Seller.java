package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Seller implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String emnail;
	private Date birthDate;
	private Double salary;
	
	private Departments department;

	public Seller() {}

	public Seller(Integer id, String name, String emnail, Date birthDate, Double salary, Departments department) {
		//super();
		this.id = id;
		this.name = name;
		this.emnail = emnail;
		this.birthDate = birthDate;
		this.salary = salary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmnail() {
		return emnail;
	}

	public void setEmnail(String emnail) {
		this.emnail = emnail;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", emnail=" + emnail + ", birthDate=" + birthDate + ", salary="
				+ salary + ", department=" + department + "]";
	}
	
	

}
