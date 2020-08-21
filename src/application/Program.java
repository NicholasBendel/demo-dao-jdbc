package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {
	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Teste update departamento");
		Department department = new Department(7,"Update confirmado");
		departmentDao.update(department);
		System.out.println(department);
		
	}
}
