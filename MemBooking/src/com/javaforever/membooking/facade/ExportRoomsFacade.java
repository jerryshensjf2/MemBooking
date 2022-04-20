package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.RoomService;
import com.javaforever.membooking.serviceimpl.RoomServiceImpl;
import com.javaforever.membooking.utils.POIExcelUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;

public class ExportRoomsFacade extends HttpServlet{

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
		try(OutputStream out = response.getOutputStream()){
			try{
				RoomService service  = new RoomServiceImpl();
				response.addHeader("Content-Disposition", "attachment;filename=Rooms.xls");
				List<Room> list = service.listAllRooms();
				List<List<String>> contents = new ArrayList<>();
				String sheetName = "Room信息表";
				String [] headers = {"Room No.","描述","Room Name","Active","ID"};
				
				for (Room room:list) {
					String [] row = {room.getRoomNo(),room.getDescription(),room.getRoomName(),room.getActive().toString(),room.getId().toString()};
					contents.add(Arrays.asList(row));
				}
				
				POIExcelUtil.exportExcelSheet(out, sheetName, Arrays.asList(headers), contents);
			} catch (Exception e) {
				ServletException se = new ServletException();
				se.setStackTrace(e.getStackTrace());
				throw se;
			}
		}
	}

}
