package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT seller.Id, seller.Name, seller.Email, seller.BirthDate, seller.BaseSalary, seller.DepartmentId, department.Name as DepName"
							+ " FROM seller" + " INNER JOIN department ON seller.DepartmentId = department.Id"
							+ " WHERE seller.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				Department dept = instantiateDepartment(rs);
				return instantiateSeller(rs, dept);
			}

			return null;

		} catch (SQLException e) {

			throw new DBException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dept) throws SQLException {
		return new Seller(rs.getInt("Id"), rs.getString("Name"), rs.getString("Email"), rs.getDate("BirthDate"),
				rs.getDouble("BaseSalary"), dept);
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT seller.Id, seller.Name, seller.Email, seller.BirthDate, seller.BaseSalary, seller.DepartmentId, department.Name as DepName"
							+ " FROM seller" + " INNER JOIN department ON seller.DepartmentId = department.Id"
							+ " ORDER BY seller.Name");

			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dept = map.get(rs.getInt("DepartmentId"));

				if (dept == null) {

					dept = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dept);
				}

				Seller obj = instantiateSeller(rs, dept);
				list.add(obj);
			}

			return list;

		} catch (SQLException e) {

			throw new DBException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT seller.Id, seller.Name, seller.Email, seller.BirthDate, seller.BaseSalary, seller.DepartmentId, department.Name as DepName"
							+ " FROM seller" + " INNER JOIN department ON seller.DepartmentId = department.Id"
							+ " WHERE department.Id = ?" + " ORDER BY seller.Name");

			st.setInt(1, department.getId());
			rs = st.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department dept = map.get(rs.getInt("DepartmentId"));

				if (dept == null) {

					dept = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dept);
				}

				Seller obj = instantiateSeller(rs, dept);
				list.add(obj);
			}

			return list;

		} catch (SQLException e) {

			throw new DBException(e.getMessage());

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
