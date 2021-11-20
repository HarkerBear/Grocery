package com.grocery.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.grocery.demo.entity.Item;
import com.grocery.demo.service.ItemService;
import com.grocery.demo.utils.PageModel;

/**
 * Servlet implementation class ManagmentControllor
 */
@WebServlet("/management")
public class ManagmentControllor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService =new ItemService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagmentControllor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");//1-list 2-showAdd 3-add 3-showUpdate
		if(method.equals("1")) {
			this.list(request,response);
		}else if(method.equals("2")) {
			this.showAdd(request,response);
		}else if(method.equals("3")) {
			this.add(request,response);
		}else if(method.equals("4")) {
			this.showUpdate(request,response);
		}else if(method.equals("5")) {
			this.update(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String p=request.getParameter("p");
		String r=request.getParameter("r");
		if(p==null) p="1";
		if(r==null) r="10";
		PageModel pageModel=itemService.pagination(Integer.parseInt(p),Integer.parseInt(r));
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
	}
	
	private void showAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//1. Initialize FileUpload
		FileItemFactory factory=new DiskFileItemFactory(); //transfer data
		ServletFileUpload sf=new ServletFileUpload(factory); //Http support
		//2. Traversal FileItems
		try {
			List<FileItem> formData=sf.parseRequest(request);
			Item item=new Item();
			for(FileItem fi:formData) {
				if(fi.isFormField()) {
					switch (fi.getFieldName()) {
					case "name":
						item.setName(fi.getString("UTF-8"));
						break;
					case "category":
						item.setCategory(Integer.parseInt(fi.getString("UTF-8")));
						break;
					case "price":
						item.setPrice(Double.parseDouble(fi.getString("UTF-8")));
						break;
					case "description":
						item.setDescription(fi.getString("UTF-8"));
						break;
					}
				}else {
					String path=request.getServletContext().getRealPath("/upload");
					System.out.println(path);
					String fileName=UUID.randomUUID().toString();
					String suffix=fi.getName().substring(fi.getName().lastIndexOf("."));
					fi.write(new File(path,fileName+suffix));
					item.setPreview("/upload/"+fileName+suffix);
				}
			}
			itemService.add(item);
			response.sendRedirect("/management?method=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		Item item=ItemService.findById(id);
		request.setAttribute("item", item);
		request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

}
