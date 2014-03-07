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
	public String getFriendNameFirstAlpha() {
		return friendNameFirstAlpha;
	}
	public void setFriendNameFirstAlpha(String friendNameFirstAlpha) {
		this.friendNameFirstAlpha = friendNameFirstAlpha;
	}
	
}
