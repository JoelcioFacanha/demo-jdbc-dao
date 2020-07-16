package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== Test 01 Seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("=== Test 01 Seller findByDepartment ===");
		Department d = new Department(2, "");
		List<Seller> list = sellerDao.findByDepartment(d);

		for (Seller s : list) {
			System.out.println(s);
		}

		System.out.println("=== Test 01 Seller findALL ===");
		list = sellerDao.findAll();

		for (Seller s : list) {
			System.out.println(s);
		}

	}

}
