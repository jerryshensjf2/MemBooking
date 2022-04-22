package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.RoomService;
import com.javaforever.membooking.serviceimpl.RoomServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@WebServlet(name = "findRoomByIdFacade",urlPatterns = "/facade/findRoomByIdFacade")
public class FindRoomByIdFacade extends HttpServlet{

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
			Long id = Long.parseLong(request.getParameter("id"));
			RoomService service = new RoomServiceImpl();
			Room room = service.findRoomById(id);
			result.put("success",true);
			result.put("data",room);
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
