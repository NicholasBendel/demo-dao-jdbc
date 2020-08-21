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
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		
	}

	@Override
	public void update(Seller obj) {
		
	}

	@Override
	public void deleteById(Integer id) {
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select seller.*,department.Name as DepName "
									 + "from seller inner join department "
									 + "on seller.DepartmentId = department.Id "
									 + "where seller.Id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()){
				Department dep = instantiateDepartment(rs);
				return instantiateSeller(rs, dep);
			}
			
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("DepartmentId"),
							  rs.getString("DepName"));
	}
	
	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException{
		return new Seller(rs.getInt("Id"),
					      rs.getString("Name"),
					      rs.getString("Email"),
					      rs.getDate("BirthDate"),
					      rs.getDouble("BaseSalary"),
					      department);
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("select seller.*,department.Name as DepName "
									 + "from seller inner join department "
									 + "on seller.DepartmentId = department.Id "
									 + "where DepartmentId = ? "
									 + "order by name");
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()){
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				list.add(instantiateSeller(rs, dep));
			}
			
			return list;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
