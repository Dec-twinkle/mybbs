package dao;

import java.util.List;

import domain.Reply;
import domain.User;

public interface ReplyDao {
	/*
	 * 根据itemId得到ReplyList
	 */
	public abstract List<Reply> getReplyList(int itemId);
	/*
	 * 插入Reply
	 */
	public abstract void createReply(Reply reply);
	public abstract List<User> getAuthor(int itemId);
}
