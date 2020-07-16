package application;

import java.sql.Date;
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

		System.out.println("=== Test 02 Seller findByDepartment ===");
		Department d = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(d);

		for (Seller s : list) {
			System.out.println(s);
		}

		System.out.println("=== Test 03 Seller findALL ===");
		list = sellerDao.findAll();

		for (Seller s : list) {
			System.out.println(s);
		}

		System.out.println("=== Test 04 Seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(0L), 4000.0, d);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());

		System.out.println("=== Test 05 Seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");

		System.out.println(seller);

		sellerDao.update(seller);

	}

}
