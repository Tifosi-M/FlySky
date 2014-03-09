package com.findyou.domain.entity;

import com.hp.hpl.sparta.xpath.TrueExpr;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="friends")
public class Friends {

	public Friends(){
		
	}
	
	@DatabaseField(generatedId=true)
	private String friendId;
	
	@DatabaseField(canBeNull=false)
	private String friendName;
	
	@DatabaseField(canBeNull=false)
	private String friendSex;
	
	@DatabaseField(canBeNull=false)
	private String friendMail;
	
	@DatabaseField(canBeNull=false)
	private String friendQQ;
	
	@DatabaseField(canBeNull=false)
	private String friendHobby;
	
	@DatabaseField(canBeNull=false)
	private String friendCity;
	
	@DatabaseField(canBeNull=false)
	private String friendNameFirstAlpha;

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendSex() {
		return friendSex;
	}

	public void setFriendSex(String friendSex) {
		this.friendSex = friendSex;
	}

	public String getFriendMail() {
		return friendMail;
	}

	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}

	public String getFriendQQ() {
		return friendQQ;
	}

	public void setFriendQQ(String friendQQ) {
		this.friendQQ = friendQQ;
	}

	public String getFriendHobby() {
		return friendHobby;
	}

	public void setFriendHobby(String friendHobby) {
		this.friendHobby = friendHobby;
	}

	public String getFriendCity() {
		return friendCity;
	}

	public void setFriendCity(String friendCity) {
		this.friendCity = friendCity;
	}

	public String getFriendNameFirstAlpha() {
		return friendNameFirstAlpha;
	}

	public void setFriendNameFirstAlpha(String friendNameFirstAlpha) {
		this.friendNameFirstAlpha = friendNameFirstAlpha;
	}
	
	
}
