package com.flysky.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.flysky.domain.CampusMemo;
@Repository
public class CampusDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void save(CampusMemo campusMemo){
		Random random=new Random(System.currentTimeMillis());
		Integer rdInt=random.nextInt(1000);
		String rdStr = rdInt.toString();
		String sql = "insert into campus values(?,?,?,?,?,?,?)";
		Object[] params=new Object[]{rdStr,campusMemo.getCampusName(),campusMemo.getCampusBy()
				,campusMemo.getCampusTime(),"N",campusMemo.getCampusAddress(),campusMemo.getCampusContent()};
		jdbcTemplate.update(sql, params);
	}
	
	public List<CampusMemo> getAllCampus(){
		String sql = "select * from campus";
		final List<CampusMemo> list = new ArrayList<CampusMemo>();
		jdbcTemplate.query(sql,new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet re) throws SQLException {
				CampusMemo campusMemo = new CampusMemo();
				campusMemo.setCampusId(re.getString("campusid"));
				campusMemo.setCampusAddress(re.getString("campusaddress"));
				campusMemo.setCampusBy(re.getString("campusby"));
				campusMemo.setCampusContent(re.getString("campuscontent"));
				campusMemo.setCampusName(re.getString("campusname"));
				campusMemo.setCampusTime(re.getString("campustime"));
				list.add(campusMemo);
			}
		});
		return list;
	}
	public void deleteById(String id){
		String sql = "delete from campus where campusid=?";
		jdbcTemplate.update(sql,new Object[]{id});
		
	}
	
}
