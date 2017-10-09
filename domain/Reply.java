package domain;

public class Reply {
	private int replyId;
	private int authorId;
	private int itemId;
	private String cont;
	public Reply(int replyId, int authorId, int itemId, String cont) {
		super();
		this.replyId = replyId;
		this.authorId = authorId;
		this.itemId = itemId;
		this.cont = cont;
	}
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", authorId=" + authorId
				+ ", itemId=" + itemId + ", cont=" + cont + "]";
	};
	
}
