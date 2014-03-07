package com.businesscard.domain.Service;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.businesscard.domain.Emtity.NormalCardInfo;
import com.businesscard.domain.Emtity.UserCard;
import com.businesscard.domain.IService.INormalCardInfoService;

public class NormalCardInfoService implements INormalCardInfoService {

	private SQLiteDatabase db;
	private Cursor cursor;
	private String sql;

	public NormalCardInfoService(SQLiteDatabase db) {
		this.db = db;
	}

	@Override
	public NormalCardInfo findMyCardByUserPhone(String userId) {
		// TODO Auto-generated method stub
		NormalCardInfo normalCardInfo = new NormalCardInfo();
		sql = "select * from UserInfo where userId='" + userId + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				normalCardInfo.setUserId(userId);
				int userPhoneColumn = cursor.getColumnIndex("userPhone");
				String userPhone = cursor.getString(userPhoneColumn);
				normalCardInfo.setUserPhone(userPhone);
				int userNameColumn = cursor.getColumnIndex("userName");
				String userName = cursor.getString(userNameColumn);
				normalCardInfo.setUserName(userName);
				int userTelColumn = cursor.getColumnIndex("userTel");
				String userTel = cursor.getString(userTelColumn);
				normalCardInfo.setUserTel(userTel);
				int userEmailColumn = cursor.getColumnIndex("userEmail");
				String userEmail = cursor.getString(userEmailColumn);
				normalCardInfo.setUserEmail(userEmail);
				int userProfessionalColumn = cursor
						.getColumnIndex("userProfessional");
				String userProfessional = cursor
						.getString(userProfessionalColumn);
				normalCardInfo.setUserProfessional(userProfessional);
				int userAddressColumn = cursor.getColumnIndex("userAddress");
				String userAddress = cursor.getString(userAddressColumn);
				normalCardInfo.setUserAddress(userAddress);
				int userImageColumn = cursor.getColumnIndex("userImage");
				String userImage = cursor.getString(userImageColumn);
				normalCardInfo.setUserImage(userImage);
				int userQQColumn = cursor.getColumnIndex("userQQ");
				String userQQ = cursor.getString(userQQColumn);
				normalCardInfo.setUserQQ(userQQ);

			}
			return normalCardInfo;
		}
	}

	@Override
	public List<NormalCardInfo> findNormalCardsByUserPhone(String userId) {
		// TODO Auto-generated method stub
		List<NormalCardInfo> normalCardInfo_list = new ArrayList<NormalCardInfo>();
		NormalCardInfo normalCardInfo = new NormalCardInfo();
		sql = "select * from UserNormalCard order by userName desc";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				normalCardInfo.setUserId(userId);
				int userPhoneColumn = cursor.getColumnIndex("userPhone");
				String userPhone = cursor.getString(userPhoneColumn);
				normalCardInfo.setUserPhone(userPhone);
				int userNameColumn = cursor.getColumnIndex("userName");
				String userName = cursor.getString(userNameColumn);
				normalCardInfo.setUserName(userName);
				int userTelColumn = cursor.getColumnIndex("userTel");
				String userTel = cursor.getString(userTelColumn);
				normalCardInfo.setUserTel(userTel);
				int userEmailColumn = cursor.getColumnIndex("userEmail");
				String userEmail = cursor.getString(userEmailColumn);
				normalCardInfo.setUserEmail(userEmail);
				int userProfessionalColumn = cursor
						.getColumnIndex("userProfessional");
				String userProfessional = cursor
						.getString(userProfessionalColumn);
				normalCardInfo.setUserProfessional(userProfessional);
				int userAddressColumn = cursor.getColumnIndex("userAddress");
				String userAddress = cursor.getString(userAddressColumn);
				normalCardInfo.setUserAddress(userAddress);
				int userImageColumn = cursor.getColumnIndex("userImage");
				String userImage = cursor.getString(userImageColumn);
				normalCardInfo.setUserImage(userImage);
				int userQQColumn = cursor.getColumnIndex("userQQ");
				String userQQ = cursor.getString(userQQColumn);
				normalCardInfo.setUserQQ(userQQ);
				normalCardInfo_list.add(normalCardInfo);
			}
			return normalCardInfo_list;
		}
	}

	@Override
	public boolean addMyCard(NormalCardInfo normalCardInfo) {
		// TODO Auto-generated method stub
		sql = "insert into UserInfo (userId,userPhone,userName,userTel,userEmail,userProfessional,userAddress,userImage,userQQ ) values ('"
				+ normalCardInfo.getUserId()
				+ "','"
				+ normalCardInfo.getUserPhone()
				+ "','"
				+ normalCardInfo.getUserName()
				+ "','"
				+ normalCardInfo.getUserTel()
				+ "','"
				+ normalCardInfo.getUserEmail()
				+ "','"
				+ normalCardInfo.getUserProfessional()
				+ "','"
				+ normalCardInfo.getUserAddress()
				+ "','1','"
				+ normalCardInfo.getUserQQ() + "')";

		db.execSQL(sql);

		return true;
	}

	@Override
	public boolean addNormalCardByUserPhone(String userNameFirstAlpha,
			NormalCardInfo normalCardInfo) {
		// TODO Auto-generated method stub
		sql = "insert into UserNormalCard (userId,userPhone,userName,userNameFirstAlpha,userTel,userEmail,userProfessional,userAddress,userImage,userQQ ) values ('"
				+ normalCardInfo.getUserId()
				+ "','"
				+ normalCardInfo.getUserPhone()
				+ "','"
				+ normalCardInfo.getUserName()
				+ "','"
				+ userNameFirstAlpha
				+ "','"
				+ normalCardInfo.getUserTel()
				+ "','"
				+ normalCardInfo.getUserEmail()
				+ "','"
				+ normalCardInfo.getUserProfessional()
				+ "','"
				+ normalCardInfo.getUserAddress()
				+ "','1','"
				+ normalCardInfo.getUserQQ() + "')";

		db.execSQL(sql);

		return true;
	}

	@Override
	public boolean alterMyCard(NormalCardInfo normalCardInfo) {
		// TODO Auto-generated method stub
		sql = "update UserInfo set userPhone='" + normalCardInfo.getUserPhone()
				+ "',userName='" + normalCardInfo.getUserName() + "',userTel='"
				+ normalCardInfo.getUserTel() + "',userEmail='"
				+ normalCardInfo.getUserEmail() + "',userProfessional='"
				+ normalCardInfo.getUserProfessional() + "',userAddress='"
				+ normalCardInfo.getUserAddress() + "',userQQ='"
				+ normalCardInfo.getUserQQ() + "'";
		db.execSQL(sql);

		return true;
	}

	@Override
	public boolean delNormalCardByUserPhone(String userPhone) {
		// TODO Auto-generated method stub
		sql = "delete from UserNormalCard where userPhone='"+userPhone+"'";
		db.execSQL(sql);

		return true;
	}

	public Cursor findNormalCardsByUserId(String userId) {
		sql = "select a.[rowid] as _id,* from UserNormalCard as a order by userNameFirstAlpha asc";
		cursor = db.rawQuery(sql, null);
		return cursor;
	}

	public String CardInfoToString(NormalCardInfo normalCardInfo) {
		String cardInfo = null;
		cardInfo = normalCardInfo.getUserPhone() + ";"
				+ normalCardInfo.getUserName() + ";";
		if (normalCardInfo.getUserTel().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserTel() + ";";
		}
		if (normalCardInfo.getUserEmail().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserEmail() + ";";
		}
		if (normalCardInfo.getUserProfessional().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserProfessional() + ";";
		}
		if (normalCardInfo.getUserAddress().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserAddress() + ";";
		}
		if (normalCardInfo.getUserImage().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserImage() + ";";
		}
		if (normalCardInfo.getUserQQ().equals("")) {
			cardInfo = cardInfo + "null;";
		} else {
			cardInfo = cardInfo + normalCardInfo.getUserQQ() + ";";
		}
		return cardInfo;

	}

	@Override
	public boolean alterNormalCard(NormalCardInfo normalCardInfo,
			String userId, String apla) {
		// TODO Auto-generated method stub
		sql = "update UserNormalCard set userPhone='"
				+ normalCardInfo.getUserPhone() + "',userName='"
				+ normalCardInfo.getUserName() + "',userNameFirstAlpha='"
				+ apla + "',userTel='" + normalCardInfo.getUserTel()
				+ "',userEmail='" + normalCardInfo.getUserEmail()
				+ "',userProfessional='" + normalCardInfo.getUserProfessional()
				+ "',userAddress='" + normalCardInfo.getUserAddress()
				+ "',userQQ='" + normalCardInfo.getUserQQ()
				+ "' where userId='" + userId + "'";
		db.execSQL(sql);

		return true;
	}
	
	public UserCard findCardByUserPhone(String userId) {
		// TODO Auto-generated method stub
		UserCard userCard = new UserCard();
		sql = "select * from UserInfo where userId='" + userId + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				userCard.setUserId(userId);
				int userPhoneColumn = cursor.getColumnIndex("userPhone");
				String userPhone = cursor.getString(userPhoneColumn);
				userCard.setUserPhone(userPhone);
				int userNameColumn = cursor.getColumnIndex("userName");
				String userName = cursor.getString(userNameColumn);
				userCard.setUserName(userName);
				int userTelColumn = cursor.getColumnIndex("userTel");
				String userTel = cursor.getString(userTelColumn);
				userCard.setUserTel(userTel);
				int userEmailColumn = cursor.getColumnIndex("userEmail");
				String userEmail = cursor.getString(userEmailColumn);
				userCard.setUserEmail(userEmail);
				int userProfessionalColumn = cursor
						.getColumnIndex("userProfessional");
				String userProfessional = cursor
						.getString(userProfessionalColumn);
				userCard.setUserProfessional(userProfessional);
				int userAddressColumn = cursor.getColumnIndex("userAddress");
				String userAddress = cursor.getString(userAddressColumn);
				userCard.setUserAddress(userAddress);
				int userImageColumn = cursor.getColumnIndex("userImage");
				String userImage = cursor.getString(userImageColumn);
				userCard.setImage(userImage);
				int userQQColumn = cursor.getColumnIndex("userQQ");
				String userQQ = cursor.getString(userQQColumn);
				userCard.setUserQQ(userQQ);

			}
			return userCard;
		}
	}

	@Override
	public NormalCardInfo findNormalCardByUserPhone(String userId) {
		// TODO Auto-generated method stub
		NormalCardInfo normalCardInfo = new NormalCardInfo();
		sql = "select * from UserNormalCard where userPhone='" + userId + "'";
		cursor = db.rawQuery(sql, null);
		if (cursor.moveToFirst() == false) {
			return null;
		} else {
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {
				normalCardInfo.setUserId(userId);
				int userPhoneColumn = cursor.getColumnIndex("userPhone");
				String userPhone = cursor.getString(userPhoneColumn);
				normalCardInfo.setUserPhone(userPhone);
				int userNameColumn = cursor.getColumnIndex("userName");
				String userName = cursor.getString(userNameColumn);
				normalCardInfo.setUserName(userName);
				int userTelColumn = cursor.getColumnIndex("userTel");
				String userTel = cursor.getString(userTelColumn);
				normalCardInfo.setUserTel(userTel);
				int userEmailColumn = cursor.getColumnIndex("userEmail");
				String userEmail = cursor.getString(userEmailColumn);
				normalCardInfo.setUserEmail(userEmail);
				int userProfessionalColumn = cursor
						.getColumnIndex("userProfessional");
				String userProfessional = cursor
						.getString(userProfessionalColumn);
				normalCardInfo.setUserProfessional(userProfessional);
				int userAddressColumn = cursor.getColumnIndex("userAddress");
				String userAddress = cursor.getString(userAddressColumn);
				normalCardInfo.setUserAddress(userAddress);
				int userImageColumn = cursor.getColumnIndex("userImage");
				String userImage = cursor.getString(userImageColumn);
				normalCardInfo.setUserImage(userImage);
				int userQQColumn = cursor.getColumnIndex("userQQ");
				String userQQ = cursor.getString(userQQColumn);
				normalCardInfo.setUserQQ(userQQ);

			}
			return normalCardInfo;
		}
	}
}
