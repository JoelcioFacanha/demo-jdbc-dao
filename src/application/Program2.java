package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== Test 01 Department findById ===");
		Department dp = departmentDao.findById(3);
		System.out.println(dp);

		System.out.println("=== Test 02 Department findALL ===");
		List<Department> list = departmentDao.findAll();

		for (Department s : list) {
			System.out.println(s);
		}

		System.out.println("=== Test 03 Department insert ===");
		Department newDp = new Department(null, "Teste dpg");
		departmentDao.insert(newDp);
		System.out.println("Inserted! New id = " + newDp.getId());

		System.out.println("=== Test 04 Departmentr update ===");
		dp = departmentDao.findById(1);
		dp.setName("Computers");
		departmentDao.update(dp);

		System.out.println("=== Test 05 Department delete ===");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();

		departmentDao.deleteById(id);
		System.out.println("Delete completed!");

		sc.close();

	}

}
