package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class Program {
	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("Teste delete departamento");
		departmentDao.deleteById(7);
		
	}
}
