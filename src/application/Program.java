package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {
	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Test select departament all");
		List<Department> list = departmentDao.findAll();
		
		for(Department department : list) {
			System.out.println(department);
		}
	}
}
