package com.tianwen.core.share.entity;

@SuppressWarnings("serial")
public class TRelationRecordEntity implements java.io.Serializable {

	private int id;
	
	private String parentOpenid;
	
	private int parentMid;
	
	private String openid;
	
	private int mid;
	
	private String shareUrl;
	
	private int type;
	
	private String unionid;
	
	

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentOpenid() {
		return parentOpenid;
	}

	public void setParentOpenid(String parentOpenid) {
		this.parentOpenid = parentOpenid;
	}

	public int getParentMid() {
		return parentMid;
	}

	public void setParentMid(int parentMid) {
		this.parentMid = parentMid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
