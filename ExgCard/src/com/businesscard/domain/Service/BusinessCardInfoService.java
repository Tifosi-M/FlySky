package com.businesscard.domain.Service;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.businesscard.domain.Emtity.BusinessCardInfo;
import com.businesscard.domain.IService.IBusinessCardInfo;

public class BusinessCardInfoService implements IBusinessCardInfo {
	private SQLiteDatabase db;
	private Cursor cursor;
	private String sql;

	public BusinessCardInfoService(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public List<BusinessCardInfo> findBusinessCard() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<BusinessCardInfo> findBusinessCardByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Cursor findBusinessCards() {
		sql = "select a.[rowid] as _id,* from UserBusinessCard as a";
		cursor = db.rawQuery(sql, null);
		return cursor;

	}

	@Override
	public boolean AddBusinessCard(BusinessCardInfo businessCardInfo) {
		sql = "insert into UserBusinessCard (BusinessId,BusinessPhone,BusinessName,BusinessAddress,BusinessImage) values ('"
				+ businessCardInfo.getBusinessId()
				+ "','"
				+ businessCardInfo.getBusinessPhone()
				+ "','"
				+ businessCardInfo.getBusinessName()
				+ "','"
				+ businessCardInfo.getBusinessAddress()
				+ "','"
				+ businessCardInfo.getBusinessImage() + "')";
		return true;

	}

	public String BussinessCardInfoToString(BusinessCardInfo bussinessCardInfo) {
		String bussinessInfo = null;
		bussinessInfo = bussinessCardInfo.getBusinessId() + ";"
				+ bussinessCardInfo.getBusinessName() + ";"
				+ bussinessCardInfo.getBusinessPhone() + ";"
				+ bussinessCardInfo.getBusinessAddress() + ";"
				+ bussinessCardInfo.getBusinessImage() + ";";
		return bussinessInfo;

	}
}
