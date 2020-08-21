package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
				return new Seller(rs.getInt("Id"),
							      rs.getString("Name"),
							      rs.getString("Email"),
							      rs.getDate("BirthDate"),
							      rs.getDouble("BaseSalary"),
							      new Department(
						    		  		rs.getInt("DepartmentId"),
						    		  		rs.getString("DepName")));
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return null;
	}

	@Override
	public List<Seller> findAll() {
		return null;
	}
}
