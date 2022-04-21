package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.service.GuestService;
import com.javaforever.membooking.serviceimpl.GuestServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public class ListAllGuestsByPageFacade extends HttpServlet{

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		processRequest(request, response);
	}

	public String getServletInfo(){
		return "Powered by Mind Rules.";
	}

	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("application/json;charset=UTF-8");
		Map<String,Object> result = new TreeMap<String,Object>();
		PrintWriter out = response.getWriter();
		try {
			boolean last = request.getParameter("last")==null?false:Boolean.parseBoolean(request.getParameter("last"));
			int pagesize = request.getParameter("pagesize")==null?10:Integer.parseInt(request.getParameter("pagesize"));
			int pagenum = request.getParameter("pagenum")==null?1:Integer.parseInt(request.getParameter("pagenum"));
			GuestService service = new GuestServiceImpl();
			int pagecount = (Integer)service.countGuestsPage(pagesize);
			if (pagenum <= 1) pagenum = 1;
			if (pagenum >= pagecount) pagenum = pagecount;
			if (last) pagenum = pagecount;
			List<Guest> guestList = service.listAllGuestsByPage(pagesize,pagenum);
			result.put("success",true);
			result.put("rows",guestList);
			result.put("pagesize",pagesize);
			result.put("page",pagenum);
			result.put("total",pagecount);
			out.print(JSONObject.fromObject(result).toString());
		} catch (Exception e){
			e.printStackTrace();
			result.put("success",false);
			result.put("reason",e.getMessage());
			out.print(JSONObject.fromObject(result).toString());
		} finally {
			out.close();
		}
	}

}
