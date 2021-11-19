package com.grocery.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.grocery.demo.entity.Item;
import com.grocery.demo.utils.PageModel;
import com.grocery.demo.utils.XmlDataSource;

public class ItemDao {
	/**
	 * Select Items Information by different pages
	 * @param page
	 * @param rows:the (maiximal) number of items on one page
	 * @return PageModel
	 */
	
	public PageModel pagination(int page,int rows) {
		List<Item> list=XmlDataSource.getRawData();
		return new PageModel(list,page,rows);
	}
	
	public PageModel pagination(int category,int page,int rows) {
		List<Item> list=XmlDataSource.getRawData();
		List<Item> categoryList=new ArrayList();
		for(Item item:list) {
			if(item.getCategory()==category) {
				categoryList.add(item);
			}
		}
		return new PageModel(categoryList,page,rows);
	}
	
	public void add(Item item) {
		XmlDataSource.append(item);
	}
	
	public static Item findById(String id) {
		List<Item> data=XmlDataSource.getRawData();
		Item item=null;
		for(Item i:data) {
			if(i.getId().equals(id)) {
				item=i;
				break;
			}
		}
		return item;
	}
}
