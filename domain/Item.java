package domain;

public class Item {
	int itemId;
	int authorId;
	String title;
	String describ;
	String cont;
	public Item(int itemId, int authorId, String title, String describ, String cont) {
		super();
		this.itemId = itemId;
		this.authorId = authorId;
		this.title = title;
		this.describ = describ;
		this.cont = cont;
	}
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescrib() {
		return describ;
	}
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", authorId=" + authorId + ", title="
				+ title + ", describ=" + describ + ", cont=" + cont + "]";
	}
	
}
