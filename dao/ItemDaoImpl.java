package dao;

import java.util.ArrayList;
import java.util.List;

import web.Page;
import domain.Item;
import domain.User;

public class ItemDaoImpl extends BaseDAO<Item>  implements ItemDao {

	@Override
	public List<Item> getItemList() {
		String sql = "SELECT itemid, authorid, title, describ, cont from items ORDER BY itemid DESC";
		
		return queryForList(sql);
	}

	@Override
	public Item getItem(int itemId) {
		String sql = "SELECT itemid, authorid, title, describ, cont from items where itemid=?";
		return query(sql, itemId);
	}

	@Override
	public void createItem(Item item) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO items (authorid,title,describ,cont) values(?,?,?,?)";
		insert(sql, item.getAuthorId(),item.getTitle(),item.getDescrib(),item.getCont());
		}

	@Override
	public void deleteItem(int itemId) {
		String sql="DELETE FROM items WHERE itemid=?";
		update(sql, itemId);
		
	}

	@Override
	public Page<Item> getPage(int pageNumber) {
		Page<Item> page = new Page<>(pageNumber);
		page.setTotalItemNumber(getTotalItemNumber());
		page.setList(getPageList(page.getPageNo(), 10));
		return page;
		
	}

	@Override
	public long getTotalItemNumber() {
		String sql="SELECT count(itemid) FROM items";
		
		return getSingleVal(sql);
	}

	@Override
	public List<Item> getPageList(int PageNumber,int PageSize) {
		String sql ="SELECT itemid, authorid, title, describ, cont from items ORDER BY itemid DESC LIMIT ?,?";
		
		return queryForList(sql, (PageNumber-1)*PageSize,PageSize);
	}

	@Override
	public List<User> getAuthor(int PageNumber) {
		List<Item> itemList=getPageList(PageNumber, 10);
		List<User> authorList=new ArrayList<User>();
		UserDao dao = new UserDaoImpl();
		for(Item item :itemList)
		{
			User author=dao.getUser(item.getAuthorId());
			authorList.add(author);
		}
		
		return authorList;
	}

	
}
