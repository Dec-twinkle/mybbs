package dao;

import java.util.List;

import web.Page;
import domain.Item;
import domain.User;

public interface ItemDao {
	/*
	 * 获取item列表
	 */
	public abstract List<Item> getItemList();
	/*
	 * 获取item对象
	 */
	public abstract Item getItem(int itemId);
	/*
	 * 创建item对象
	 */
	public abstract void createItem(Item item);
	/*
	 * 删除item对象
	 */
	public abstract void deleteItem(int itemId);
	/*
	 * 获得item的page队列
	 */
	public abstract Page<Item> getPage(int pageNumber);
	/*
	 * 获取总数
	 */
	public abstract long getTotalItemNumber();
	/*
	 * 获取page列表
	 */
	public abstract List<Item> getPageList(int PageNumber,int PageSize);
	
	public abstract List<User> getAuthor(int PageNumber);
}
