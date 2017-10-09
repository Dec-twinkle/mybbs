package domain;

public class shop {
	private int shopid;
	private int userid;
	private int prize;
	private String name;
	private String picture;
	public shop(int shopid, int userid, int prize, String name, String picture) {
		super();
		this.shopid = shopid;
		this.userid = userid;
		this.prize = prize;
		this.name = name;
		this.picture = picture;
	}
	public shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPrize() {
		return prize;
	}
	public void setPrize(int prize) {
		this.prize = prize;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "shop [shopid=" + shopid + ", userid=" + userid + ", prize="
				+ prize + ", name=" + name + ", picture=" + picture + "]";
	}
	
}
