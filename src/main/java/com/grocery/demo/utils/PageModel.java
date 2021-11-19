package com.grocery.demo.utils;

import java.util.ArrayList;
import java.util.List;

public class PageModel {
	private int page;
	private int totalPages;
	private int rows;
	private int totalRows;
	private int pageStartRow;
	private int pageEndRow;
	private boolean hasNextPage;
	private boolean hasPrePage;
	private List pageData;
	
	public PageModel() {
		super();
	}
	
	public PageModel(List data,int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
		totalRows=data.size();
		totalPages=new Double(Math.ceil(totalRows/(rows*1f))).intValue();
		pageStartRow=(page-1)*rows;
		pageEndRow=page*rows;
		if(pageEndRow>totalRows) {
			pageEndRow=totalRows;
		}
		pageData=data.subList(pageStartRow,pageEndRow);
		hasPrePage=this.page>1?true:false;
		hasNextPage=this.page<totalPages?true:false;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public List getPageData() {
		return pageData;
	}

	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	
	public static void main(String[] args) {
		List sample=new ArrayList();
		for(int i=1;i<=100;i++) {
			sample.add(i);
		}
		PageModel pageModel1=new PageModel(sample,6,8);
		System.out.println(pageModel1.getPageData());
		System.out.println(pageModel1.getTotalPages());
		System.out.println(pageModel1.getPageStartRow());
		System.out.println(pageModel1.getPageEndRow());
	}
}
