package dao;

import java.util.ArrayList;
import java.util.List;

import web.Page;

import domain.User;
import domain.shop;

public class ShopDaoImpl extends BaseDAO<shop> implements ShopDao{

	@Override
	public List<shop> getShopList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<shop> getPersonShopList(int userid) {
		String sql = "SELECT shopid, userid, name, prize, picture FROM shop WHERE userid=? ORDER BY shopid DESC";
		return queryForList(sql, userid);
	}

	@Override
	public Page<shop> getShopPage(int pageNumber) {
		Page<shop> page = new Page<>(pageNumber);
		page.setTotalItemNumber(getTotalNumber());
		page.setPageSize(9);
		
		page.setList(getPageList(page.getPageNo(), 9));
		return page;
	}

	@Override
	public long getTotalNumber() {
		String sql="SELECT count(shopid) FROM shop";
		
		return getSingleVal(sql);
	}

	@Override
	public List<shop> getPageList(int pageNumber, int pageSize) {
		String sql ="SELECT shopid, userid, prize, name, picture from shop ORDER BY shopid DESC LIMIT ?,?";
		
		return queryForList(sql, (pageNumber-1)*pageSize,pageSize);
	}

	@Override
	public List<User> getShopper(int PageNumber) {
		List<shop> shopList=getPageList(PageNumber, 9);
		List<User> authorList=new ArrayList<User>();
		UserDao dao = new UserDaoImpl();
		for(shop Shop:shopList)
		{
			User author=dao.getUser(Shop.getUserid());
			authorList.add(author);
		}
		return authorList;
	}

	@Override
	public void createShop(shop Shop) {
		String sql = "INSERT INTO shop (userid,prize,name,picture) values(?,?,?,?)";
		insert(sql, Shop.getUserid(),Shop.getPrize(),Shop.getName(),Shop.getPicture());
	}

	@Override
	public void deleteShop(int shopid) {
		String sql = "DELETE from shop WHERE shopid=?";
		update(sql, shopid);
	}

	@Override
	public shop getShop(int shopid) {
		String sql ="SELECT shopid, userid, prize, name, picture from shop WHERE shopid=?";
		return query(sql, shopid);
	}

}
