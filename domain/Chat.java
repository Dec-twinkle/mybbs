package domain;

public class Chat {
	private int chatId;
	private int senderId;
	private int receiverId;
	private String cont;
	public Chat(int chatId, int senderId, int receiverId, String cont) {
		super();
		this.chatId = chatId;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.cont = cont;
	}
	public Chat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public String getCont() {
		return cont;
	}
	public void setCont(String cont) {
		this.cont = cont;
	}
	@Override
	public String toString() {
		return "Chat [chatId=" + chatId + ", senderId=" + senderId
				+ ", receiverId=" + receiverId + ", cont=" + cont + "]";
	}
	
}
