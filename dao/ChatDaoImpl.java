package dao;

import java.util.ArrayList;
import java.util.List;

import domain.Chat;

public class ChatDaoImpl extends BaseDAO<Chat> implements ChatDao{

	@Override
	public List<Chat> getChatList(int userId1, int userId2) {
		String sql = "SELECT chatid,senderid,receiverid,cont from chat WHERE (senderid=? and receiverid=?) or (senderid=? and receiverid=?)LIMIT ?,?";
		int number=(int) totalChatNumber(userId1, userId2);
		int minnumber=((number-30)>0)?number-30:0;
		return queryForList(sql, userId1,userId2,userId2,userId1,minnumber,number);
	}

	@Override
	public void createChat(Chat chat) {
		String sql = "INSERT INTO chat (senderid,receiverid,cont) VALUES (?,?,?)";
		insert(sql, chat.getSenderId(),chat.getReceiverId(),chat.getCont());
		
	}


	@Override
	public long totalChatNumber(int userId1, int userId2) {
		String sql = "SELECT count(chatid) from chat WHERE (senderid=? and receiverid=?) or (senderid=? and receiverid=?)";
		
		return getSingleVal(sql, userId1,userId2,userId2,userId1);
	}

	@Override
	public List<Integer> getChatAuthorList(int userId1) {
		String sql = "SELECT senderid from chat where receiverid=? and senderid<>? union select receiverid from chat where senderid=? and receiverid<>?";
		List<Integer> AuthorList = new ArrayList<>();
		for(Object[] os: getListVal(sql, userId1,userId1,userId1,userId1))
		{
			int i = (int) os[0];
			AuthorList.add(i);
		}
		return AuthorList;
	}

	@Override
	public Chat getLastChat(int userId1, int userId2) {
		String sql = "SELECT chatid,senderid,receiverid,cont from chat where (senderid=? and receiverid=?) or (senderid=? and receiverid=?)"
				+ "ORDER BY chatid DESC LIMIT 1 ";
		return query(sql, userId1,userId2,userId2,userId1);
	}
	
}
