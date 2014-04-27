package com.telc.domain.IService;

import java.util.List;

import com.telc.domain.Emtity.Campus;

public interface ICampusService {
	public Campus getCampusById(String id);
	public boolean updateCampusStatus(String campusId,boolean flag);
	public List<Campus> getAllCampus();
}
