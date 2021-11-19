package com.grocery.demo.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.grocery.demo.entity.Item;

public class XmlDataSource {
	private static List<Item> items = new ArrayList<>();
	private static String itemsFile;
	static {
		itemsFile = XmlDataSource.class.getResource("/item.xml").getPath();
		reload();
		}

	public static List<Item> getRawData() {
		return items;
	}
	
	public static void append(Item item) {
		//1. Get Document
		SAXReader reader=new SAXReader();
		Writer writer=null;
		try {
			Document document=reader.read(itemsFile);
			Element root=document.getRootElement();
			//2. Create Item Node
			Element i=root.addElement("item");
			//3. Create Item's Attributes and Sub-elements
			i.addAttribute("id", String.valueOf(items.size()+1));
			i.addElement("name").setText(item.getName());
			i.addElement("category").setText(String.valueOf(item.getCategory()));
			i.addElement("price").setText(String.valueOf(item.getPrice()));
			i.addElement("preview").setText(item.getPreview());
			i.addElement("description").setText(item.getDescription());
			//4. Write into XML
			writer=new OutputStreamWriter(new FileOutputStream(itemsFile),"UTF-8");
			document.write(writer);
			System.out.println(itemsFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reload();
		}
		
	}
	
	private static void reload() {
		URLDecoder decoder = new URLDecoder();
		items.clear();
		try {
			decoder.decode(itemsFile,"UTF-8");
			System.out.println(itemsFile);
			SAXReader reader = new SAXReader();
			Document document = reader.read(itemsFile);
			List<Node> nodes = document.selectNodes("/root/item");
			for (Node node : nodes) {
				Element element = (Element) node;
				Item item = new Item();
				item.setId(element.attributeValue("id"));
				item.setName(element.elementText("name"));
				item.setCategory(Integer.parseInt(element.elementText("category")));
				item.setPrice(Double.parseDouble(element.elementText("price")));
				item.setPreview(element.elementText("preview"));
				item.setDescription(element.elementText("description"));
				items.add(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void update(Item item) {
		SAXReader reader=new SAXReader();
		Writer writer=null;
		try {
			Document document=reader.read(itemsFile);
			List<Node> nodes=document.selectNodes("/root/item[@id="+item.getId()+"]");
			if(nodes.size()==0) {
				throw new RuntimeException("id="+item.getId()+"does not exist.");
			}
			Element i=(Element)nodes.get(0);
			i.selectSingleNode("name").setText(item.getName());
			i.selectSingleNode("category").setText(String.valueOf(item.getCategory()));
			i.selectSingleNode("price").setText(String.valueOf(item.getPrice()));
			i.selectSingleNode("preview").setText(item.getPreview());
			i.selectSingleNode("description").setText(item.getDescription());
			writer=new OutputStreamWriter(new FileOutputStream(itemsFile),"UTF-8");
			document.write(writer);
			System.out.println(itemsFile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		reload();
	}
	
	public static void main(String[] args) {
//		List<Item> data=XmlDataSource.getRawData();
//		System.out.println(data);
		Item i=new Item();
		i.setName("Test");
		i.setCategory(1);
		i.setPrice(100);
		i.setPreview("/upload/10.jpg");
		i.setDescription("asdfsdfsd");
		XmlDataSource.append(i);
	}
}
