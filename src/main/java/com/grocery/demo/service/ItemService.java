package com.grocery.demo.service;

import java.util.List;

import com.grocery.demo.dao.ItemDao;
import com.grocery.demo.entity.Item;
import com.grocery.demo.utils.PageModel;
import com.grocery.demo.utils.XmlDataSource;

public class ItemService {
	private ItemDao itemDao=new ItemDao();
	public PageModel pagination(int page,int rows,String...category) {
		if(rows==0) {
			throw new RuntimeException("Invalid rows");
		}
		if(category.length==0||category[0]==null)
			return itemDao.pagination(page, rows);
		else
			return itemDao.pagination(Integer.parseInt(category[0]),page, rows);
	}
	
	public static void main(String[] args) {
		ItemService is=new ItemService();
		PageModel pm=is.pagination(1,4);
		List<Item> list=pm.getPageData();
		for(Item item:list) {
			System.out.println(item.getName());
		}
		System.out.println(pm.getPageStartRow()+":"+pm.getPageEndRow());
		
	}
	
	public void add(Item items) {
		itemDao.add(items);
	}
	
	public void update(Item items) {
		itemDao.add(items);
	}
	
	public static Item findById(String id) {
		Item i=ItemDao.findById(id);
		if(i==null) {
			throw new RuntimeException("[id=]"+id+"] not valid");
		}
		return i;
	}

}
