package dao;

import java.util.List;

import web.Page;
import domain.User;
import domain.shop;

public interface ShopDao {
	public abstract List<shop> getShopList();
	public abstract List<shop> getPersonShopList(int userid);
	public abstract Page<shop> getShopPage(int pageNumber);
	public abstract long getTotalNumber();
	public abstract List<shop> getPageList(int pageNumber,int pageSize);
	public abstract List<User> getShopper(int PageNumber);
	public abstract void createShop(shop Shop);
	public abstract void deleteShop(int shopid);
	public abstract shop getShop(int shopid);
}
