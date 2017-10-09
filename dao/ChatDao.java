package dao;

import java.util.List;

import domain.Chat;

public interface ChatDao {
	public abstract List<Chat> getChatList(int userId1,int userId2);
	public abstract void createChat(Chat chat);
	public abstract long totalChatNumber(int userId1,int userId2);
	public abstract List<Integer> getChatAuthorList(int userId1);
	public abstract Chat getLastChat(int userId1,int userId2);
}
