package com.grocery.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.grocery.demo.service.ItemService;
import com.grocery.demo.utils.PageModel;

/**
 * Servlet implementation class ItemController
 */
@WebServlet("/page")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService= new ItemService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("p");
		String rows=request.getParameter("r");
		String category=request.getParameter("c");
		if(page==null) page="1";
		if(rows==null) rows="6";
		
		PageModel pageModel=itemService.pagination(Integer.parseInt(page),Integer.parseInt(rows),category);
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

}
