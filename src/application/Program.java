package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {
	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Teste inserir departamento");
		Department department = new Department(null,"Teste");
		departmentDao.insert(department);
		System.out.println(department.getId());
		
	}
}
