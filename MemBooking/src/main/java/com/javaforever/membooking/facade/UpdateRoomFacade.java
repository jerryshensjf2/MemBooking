package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.RoomService;
import com.javaforever.membooking.serviceimpl.RoomServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

public class UpdateRoomFacade extends HttpServlet{

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
		PrintWriter out = response.getWriter();
		Map<String,Object> result = new TreeMap<String,Object>();
		try {
			Room room = new Room();
			room.setRoomNo(request.getParameter("roomNo"));
			room.setDescription(request.getParameter("description"));
			room.setRoomName(request.getParameter("roomName"));
			room.setActive(Boolean.parseBoolean(request.getParameter("active")));
			room.setId(Long.parseLong(request.getParameter("id")));
			RoomService service = new RoomServiceImpl();
			boolean success = service.updateRoom(room);
			result.put("success",success);
			result.put("data",null);
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
