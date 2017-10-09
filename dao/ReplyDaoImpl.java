package dao;

import java.util.ArrayList;
import java.util.List;

import domain.Reply;
import domain.User;

public class ReplyDaoImpl extends BaseDAO<Reply> implements ReplyDao{

	@Override
	public List<Reply> getReplyList(int itemId) {
		String sql = "SELECT replyid,authorid,itemid,cont from reply WHERE itemid=? ORDER BY replyid";
		return queryForList(sql, itemId);
	}

	@Override
	public void createReply(Reply reply) {
		String sql= "INSERT INTO reply (authorid,itemid,cont) VALUES (?,?,?)";
		insert(sql, reply.getAuthorId(),reply.getItemId(),reply.getCont());
		
	}
	public List<User> getAuthor(int itemId) {
		List<Reply> replyList=getReplyList(itemId);
		List<User> authorList=new ArrayList<User>();
		UserDao dao = new UserDaoImpl();
		for(Reply reply :replyList)
		{
			User author=dao.getUser(reply.getAuthorId());
			authorList.add(author);
		}
		
		return authorList;
	}

}
