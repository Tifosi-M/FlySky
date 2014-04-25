package com.flysky.dao;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
}
