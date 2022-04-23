package com.javaforever.membooking.facade;

import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.BookingService;
import com.javaforever.membooking.service.GuestService;
import com.javaforever.membooking.service.RoomService;
import com.javaforever.membooking.serviceimpl.BookingServiceImpl;
import com.javaforever.membooking.serviceimpl.GuestServiceImpl;
import com.javaforever.membooking.serviceimpl.RoomServiceImpl;
import com.javaforever.membooking.utils.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

@WebServlet(name = "listAvailableRoomsFacade",urlPatterns = "/facade/listAvailableRoomsFacade")
public class ListAvailableRoomsFacade extends HttpServlet{

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
			String occuDate = StringUtil.isBlank(request.getParameter("occuDate"))?null:request.getParameter("occuDate");
			System.out.println("JerryDebug:"+occuDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date bookingDate = sdf.parse(occuDate);
			List<Room> list = new ArrayList<>();
			List<Room> results = new ArrayList<>();
			if (StringUtil.isBlank(occuDate)) {				
				result.put("success",true);
				result.put("rows",list);
			}else {
				RoomService service = new RoomServiceImpl();
				list = service.listActiveRooms();
				BookingService bService = new BookingServiceImpl();
				
				List<Booking> bookings =  bService.listActiveBookings();
				
				results.addAll(list);
				for (Booking b:bookings) {			
					if (!b.getOccuDate().equals(bookingDate)) continue;
					else {
						for (int i=0;i<results.size();i++) {
							if (results.get(i).getId()== b.getRoomId()) {
								results.remove(i);
							}
						}
					}
				}
			}
			result.put("success",true);
			result.put("rows",results);
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
